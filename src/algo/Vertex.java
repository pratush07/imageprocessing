package algo;
import java.awt.Color;
public class Vertex 
{
    int x;
    int y;
    int node;
    Picture pic;
    
    Vertex(int a, int b,int n,Picture p)
    {
        x=a;
        y=b;
        node=n;
        pic=p;
    }
    
    int getx()
    {
        return x;
    }
     int gety()
    {
        return y;
    }
    public  double energy()            // energy of pixel at column x and row y in current picture
   {
       double e;
       double Dx;
       double Dy;
       
       if(x==0 || y==0 || x==(pic.width()-1) || y==(pic.height()-1) )
       {
           e = 195075;
           return e;
       }
       else
       {
           Color objx = new Color(pic.get_RGB(x-1,y));
           Color objx1 = new Color(pic.get_RGB(x+1,y));
           double Rx = objx1.getRed()-objx.getRed();
           double Gx = objx1.getGreen()-objx.getGreen();
           double Bx = objx1.getBlue()-objx.getBlue();
           double Rxs = Rx*Rx;
           double Gxs = Gx*Gx;
           double Bxs = Bx*Bx;
           Dx = Rxs+Gxs+Bxs;
           
           Color objy = new Color(pic.get_RGB(x,y-1));
           Color objy1 = new Color(pic.get_RGB(x,y+1));
           double Ry = objy1.getRed()-objy.getRed();
           double Gy = objy1.getGreen()-objy.getGreen();
           double By = objy1.getBlue()-objy.getBlue();
           double Rys = Ry*Ry;
           double Gys = Gy*Gy;
           double Bys = By*By;
           Dy = Rys+Gys+Bys;
           e = Dx+Dy;
           return e;
       } 
        
       }
           
    
}
