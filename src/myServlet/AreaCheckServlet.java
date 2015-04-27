package myServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String x_in = new String("0");
        String y_in = new String("0");
        String r_in = new String("0");

        try {

            x_in = request.getParameter("x");
            y_in = request.getParameter("y");
            r_in = request.getParameter("r");

        } catch (NullPointerException npe) {

            request.getRequestDispatcher("/index.jsp").forward(request, response);

        }

        if (isNumeric(x_in) && isNumeric(y_in) && isNumeric(r_in)) {

            float x = Float.parseFloat(x_in);
            float y = Float.parseFloat(y_in);
            float r = Float.parseFloat(r_in);

            if(checkRange(x, y, r)) {

                HttpSession session = request.getSession();

                session.setAttribute("x_val", x_in);
                session.setAttribute("y_val", y_in);
                session.setAttribute("r_val", r_in);
                session.setAttribute("is_in_area", isInArea(x, y, r));
                session.setAttribute("actuality", true);

            }

        }

            request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* HERE WE NEED FORWARD TO OUR JSP */

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        request.getRequestDispatcher("/").forward(request, response);

    }

    /* Check for number */

    public static boolean isNumeric(String str) {

        try {

            double d = Double.parseDouble(str);

        } catch(NumberFormatException nfe) {

            return false;

        }

        return true;

    }

    public static boolean checkRange(float x, float y, float r) {

        if( (x < -4.) || (x > 4.) )
            return false;

        if( (y < -3.) || (y > 3.) )
            return false;

        if( (r < 1) || (r > 5) )
            return false;

        return true;

    }

    /* Function to get number of quarter */

    private int getNumberQuarter(float x, float y){

        if(x > 0)
            return (y >= 0) ? 1 : 4;

        if(x == 0)
            return (y == 0) ? 0 : ( (y > 0) ? 1 : 4 );

        else
            return (y > 0) ? 2 : 3;

    }

/* Function to check in Sub Area */

    private boolean isInSubArea(float x, float y, float r, int number){

        switch(number){

            case 0:
                return true;

            case 1:

                return ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= r ) ? true : false;

            case 2:

                return ( ( (x + r) / 2. ) >= y ) ? true : false;

            case 3:

                if( x >= -r ) {

                    if ( y >= -r ) {

                        return true;

                    }
                }

                return false;

        }

        return false;

    }

/* Function to check point */

    public boolean isInArea(float x, float y, float r){

        int numberOfQuarter = getNumberQuarter(x, y);

        return isInSubArea(x, y, r, numberOfQuarter);

    }

}
