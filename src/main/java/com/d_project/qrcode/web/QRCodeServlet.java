package com.d_project.qrcode.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_project.qrcode.ErrorCorrectLevel;
import com.d_project.qrcode.QRCode;

/**
* QR code servlet .
 * It is a servlet that responds with a monochrome GIF image of QR code .
 * <br> [ Parameters ]
 * <br> O: output format (text / plain, image / gif, image / jpeg, one of the image / png, image / gif if it is omitted )
 * <br> T: model number ( 0 : Automatic , 0 if the 1-10 , has been omitted )
 * <br> E: error correction level (L, Q, M, either H, H when it is omitted )
 * <br> D: text data ( string )
 * <br> M: margin ( 0-32 , 0 if it is omitted )
 * <br> S: cell size ( 1-4 , 1 if it is omitted )
 * @author Kazuhiko Arase
 * @see com.d_project.qrcode.Mode
 * @see com.d_project.qrcode.ErrorCorrectLevel
 *
 * original source from https://kazuhikoarase.github.io/qrcode-generator/
 */
@SuppressWarnings("serial")
public class QRCodeServlet extends HttpServlet {

	private String defaultCharacterEncoding;
	
    /**
     * コンストラクタ
     */
    public QRCodeServlet() {
    }

    @Override
	public void init(ServletConfig config) throws ServletException {

    	super.init(config);

		defaultCharacterEncoding = getServletConfig().getInitParameter("default-character-encoding");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String data = getParameter(request, "d", "qrcode");

		if (defaultCharacterEncoding != null) {
			data = new String(data.getBytes("ISO-8859-1"), defaultCharacterEncoding);
			data = String.format("%s://%s:%s%s/%s;jsessionid=%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), "index.html", request.getSession(false).getId());
		}

		String output = getParameter(request, "o", "image/gif");

		int typeNumber = getIntParameter(request, "t", 0);
		if (typeNumber < 0 || 10 < typeNumber) {
    		throw new IllegalArgumentException("illegal type number : " + typeNumber);
		}

		int margin = getIntParameter(request, "m", 2);
		if (margin < 0 || 32 < margin) {
    		throw new IllegalArgumentException("illegal margin : " + margin);
		}
		
		int cellSize = getIntParameter(request, "s", 1);
		if (cellSize < 1 || 4 < cellSize) {
    		throw new IllegalArgumentException("illegal cell size : " + cellSize);
		}

		int errorCorrectLevel = parseErrorCorrectLevel(getParameter(request, "e", "H") );

    	QRCode qrcode = getQRCode(data,	typeNumber, errorCorrectLevel);

        if ("text/plain".equals(output) ) {

            response.setContentType("text/plain");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "ISO-8859-1") );

            try {
                for (int row = 0; row < qrcode.getModuleCount(); row++) {
                    for (int col = 0; col < qrcode.getModuleCount(); col++) {
                        out.print(qrcode.isDark(row, col)? "1" : "0");
                    }
                    out.print("\r\n");
                }
            } finally {
                out.close();
            }

        } else if ("image/jpeg".equals(output) ) {

            BufferedImage image = qrcode.createImage(cellSize, margin);
    
            response.setContentType("image/jpeg");
    
            OutputStream out = new BufferedOutputStream(response.getOutputStream() );
    
            try {
                ImageIO.write(image, "jpeg", out);
            } finally {
                out.close();
            }

        } else if ("image/png".equals(output) ) {

            BufferedImage image = qrcode.createImage(cellSize, margin);
    
            response.setContentType("image/png");
    
            OutputStream out = new BufferedOutputStream(response.getOutputStream() );
    
            try {
                ImageIO.write(image, "png", out);
            } finally {
                out.close();
            }

        } else if ("image/gif".equals(output) ) {

            GIFImage image = createGIFImage(qrcode, cellSize, margin);
    
            response.setContentType("image/gif");
    
            OutputStream out = new BufferedOutputStream(response.getOutputStream() );
    
            try {
                image.write(out);
            } finally {
                out.close();
            }

        } else {
            throw new IllegalArgumentException("illegal output type : " + output);
        }
	}

	private static String getParameter(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getParameter(name);
		return (value != null)? value : defaultValue;
	}

	private static int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
		String value = request.getParameter(name);
		return (value != null)? Integer.parseInt(value) : defaultValue;
	}
    
	private static QRCode getQRCode(String text, int typeNumber, int errorCorrectLevel) {

		if (typeNumber == 0) {

			return QRCode.getMinimumQRCode(text, errorCorrectLevel);

		} else {

			QRCode qr = new QRCode();
			qr.setTypeNumber(typeNumber);
			qr.setErrorCorrectLevel(errorCorrectLevel);
			qr.addData(text);
			qr.make();
			
			return qr;

		}
	}
	    
    private static int parseErrorCorrectLevel(String ecl) {

		if ("L".equals(ecl) ) {
    		return ErrorCorrectLevel.L;
    	} else if ("Q".equals(ecl) ) {
    		return ErrorCorrectLevel.Q;
    	} else if ("M".equals(ecl) ) {
	    	return ErrorCorrectLevel.M;
    	} else if ("H".equals(ecl) ) {
    		return ErrorCorrectLevel.H;
    	} else {
    		throw new IllegalArgumentException("invalid error correct level : " + ecl);
    	}

    }

    /**
     * GIFイメージを取得する。
     * @param cellSize セルのサイズ(pixel)
     * @param margin 余白(pixel)
     */
    private static GIFImage createGIFImage(QRCode qrcode, int cellSize, int margin) throws IOException {
        
        int imageSize = qrcode.getModuleCount() * cellSize + margin * 2;

        GIFImage image = new GIFImage(imageSize, imageSize);

        for (int y = 0; y < imageSize; y++) {

            for (int x = 0; x < imageSize; x++) {

                if (margin <= x && x < imageSize - margin
                        && margin <= y && y < imageSize - margin) {
                            
                    int col = (x - margin) / cellSize;
                    int row = (y - margin) / cellSize;
    
                    if (qrcode.isDark(row, col) ) {
                        image.setPixel(x, y, 0);
                    } else {
                        image.setPixel(x, y, 1);
                    }

                } else {
                    image.setPixel(x, y, 1);
                }
            }
        }

        return image;
    }
}
