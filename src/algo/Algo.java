
package algo;
import java.awt.Color;



public class Algo {
    
        private static void showVerticalSeam(SeamCarver sc)
    {
        Picture ep = SCUtility.toEnergyPicture(sc);
        int[] verticalSeam = sc.findVerticalSeam();
        Picture epOverlay = SCUtility.seamOverlay(ep, false, verticalSeam);
        epOverlay.show();
    }

    private static void showHorizontalSeam(SeamCarver sc)
    {
        Picture ep = SCUtility.toEnergyPicture(sc);
        int[] horizontalSeam = sc.findHorizontalSeam();
        Picture epOverlay = SCUtility.seamOverlay(ep, true, horizontalSeam);
        epOverlay.show();
    }

    public static void main(String[] args) {
       Picture puchu = new Picture("C:\\Users\\Public\\Pictures\\Jellyfish.jpg");
//        int h=puchu.height();
//        int w=puchu.width();
//
//    
//         SeamCarver sm=new SeamCarver(puchu,h);
//         int i=0;
//         for(i=0;i<15;i++)
//         {
//             sm.removeVerticalSeam();
//             System.out.println("--------------------------------");
//         }
//       
         
           
              
        SeamCarver sc = new SeamCarver(puchu);
        
       // System.out.printf("Displaying horizontal seam calculated.\n");
      //  showHorizontalSeam(sc);

        //System.out.printf("Displaying vertical seam calculated.\n");
        for(int i=0;i<50;i++)
        {
            showVerticalSeam(sc);
            sc.removeVerticalSeam();
       }
        
           for(int i=0;i<50;i++)
        {
           showHorizontalSeam(sc);
            sc.removeHorizontalSeam();
        }
        sc.Show();
    }
}