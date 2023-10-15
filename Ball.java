import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends JPanel{
    int x=0;
    int y=0;
    int x1=0;
    int y1=300;
    int ballWidth=50;
    int ballHeight=50;
    int ballWidth1=30;
    int ballHeight1=30;
    int speedX=10;
    int speedY=10;
    int speedX1=10;
    int speedY1=10;
    int max_x,max_y;
    int max_x1,max_y1;

    Ball(int w,int h)
    {
        this.max_x=w-ballWidth;
        this.max_y=h-ballHeight;  
        this.max_x1=w-ballWidth;
        this.max_y1=h-ballHeight;    
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    
        g.setColor(Color.RED);

        g.fillOval(x,y,ballWidth,ballHeight);

        g.fillOval(x1,y1,ballWidth1,ballHeight1);

        if(x>max_x || x<0)
            speedX=-speedX;
        if(y>max_y || y<0)
            speedY=-speedY;
        if(x1>max_x1 || x1<0)
            speedX1=-speedX1;
        if(y1>max_y1 || y1<0)
            speedY1=-speedY1;

        x=x+speedX;
        y=y+speedY;

        x1=x1+speedX1;
        y1=y1+speedY1;

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }
}
