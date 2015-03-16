package algo;
import java.awt.Color;

public class SeamCarver {
    Picture pic;
    int count=0;
   public SeamCarver(Picture picture)
   {
       pic = picture;
      
     }
   //public Picture picture()  
void Show()
{
    pic.show();
}
// current picture
   public     int getWidth()                         // width  of current picture
   {
       return pic.width();
   }
   public     int getHeight()                        // height of current picture
   {
       return pic.height();
   }
   
   void insertdata(int x,int y,int vf,int cv,Vertex v[],int edgeto[][],double weight[])
   {
       v[vf]=new Vertex(x,y,vf,pic);
       edgeto[vf][0]=cv;
       edgeto[vf][1]=vf;
       weight[vf]=v[vf].energy()+weight[cv];
       //System.out.println("\noye mai insert se aya hu");
      // System.out.println("\nedge from"+edgeto[vf][0]+"edge to"+edgeto[vf][1]+" with weight"+weight[vf]);
       
   }
   
   void CompareData(int cv,int vf,Vertex v[],int edgeto[][],double weight[])
   {
       //System.out.println("oye mai comparing");
       if((weight[cv]+v[vf].energy())<weight[vf])
       {
           edgeto[vf][0]=cv;
           edgeto[vf][1]=vf;
           weight[vf]=v[vf].energy()+weight[cv];
          //  System.out.println("maine kapde change kr liye");
          // System.out.println("\nedge from"+edgeto[vf][0]+"edge to"+edgeto[vf][1]+" with weight"+weight[vf]);
            
       }
   }
   
     public  double energy(int x,int y)            // energy of pixel at column x and row y in current picture
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

   public   int[] findHorizontalSeam()
   {
   double min=0;
       int posmin=0;
       double prevmin=0;
       int h = this.getHeight();
       int w = this.getWidth();
       int prfc=0,row=1,vf=0;
       int c_trav=0,d=0,ptemp=0;
       Vertex v[] = new Vertex[w*h];
       int minSeam2[] = new int[w*h];
       int edgeto[][] = new int[w*h][2];
       double weight[] = new double[w*h];
       for(int i=0;i<h;i++)
       {
       v[vf]=new Vertex(row,i,vf,pic);
       edgeto[vf][0]=0;
       edgeto[vf][1]=vf;
       weight[vf]=v[vf].energy();
       ++vf;
       }
       while(row<w-1)
           {   
              //d=0;
              c_trav=0;
              ptemp=prfc;
              for(int i=0;i<h;i++)
              {
                  int cv=ptemp+c_trav;
                  
                if(c_trav==0)
                {
                  /*if((v[cv].getx()-1)>=0)
                  {  
                    insertdata(v[cv].getx()-1,row+1,vf,cv,v,edgeto,weight);
                    d++;
                    prfc=vf;
                    ++vf;
                    // System.out.println("mai pehli wali condition hu jisme mai check hota hu bound ke liye");
                  }*/
                  
                      
                  insertdata(row+1,v[cv].gety(),vf,cv,v,edgeto,weight);
                  
                  prfc=vf;
                  ++vf;
                  
                  /*if(v[cv].getx()+1<=this.getWidth()-1)
                  {*/
                      insertdata(row+1,v[cv].gety()+1,vf,cv,v,edgeto,weight);
                      ++vf;
                  /*}*/
                  ++c_trav;
                }
                else
                {
                  //System.out.println("oye else mai aagya");
                  CompareData(cv,vf-2,v,edgeto,weight);
                  CompareData(cv,vf-1,v,edgeto,weight);
                  if(v[cv].gety()+1<this.getHeight()-1)
                  {
                    insertdata(row+1,v[cv].gety()+1,vf,cv,v,edgeto,weight);
                    ++vf;
                  }
                  ++c_trav;
                }
                  
            }
               
            ++row;  
          }
       /*for(int i=0;i<(w-1)*(h-2);i++)
       {
           System.out.println(edgeto[i][0]+" to "+edgeto[i][1]);
       }*/
          min=weight[prfc];
          for(int m=prfc;m<=prfc+h-1;m++)
          {
                 if(weight[m]<min)
                 {  
                    min=weight[m];
                    posmin=m;
                    
                 } 
              
             }
               
                 int t=posmin;
                 for(int k=this.getWidth()-1;k>=0;k--)
                 {
                     //System.out.println("i am here");
                     if(k==0)
                     {
                        // System.out.println("used condition");
                         
                         minSeam2[k]=minSeam2[k+1];
                       //  System.out.println(minSeam[0]);
                     }
                     else
                     {
                     minSeam2[k]=v[t].gety();
                     t=edgeto[t][0];
                     }
                 }
//System.out.println("print starts");
//                 for(int k=0;k<h;k++)
//                 {
//                     
//                     System.out.println(k+" "+minSeam[k]);
//                     
//                 }
//  
             
     
    return minSeam2;  
   }// sequence of indices for horizontal seam in current picture
 public int[] findVerticalSeam()
    {
       double min=0;
       int posmin=0;
       double prevmin=0;
       int h = this.getHeight();
       int w = this.getWidth();
       int prfc=0,row=1,vf=0;
       int c_trav=0,d=0,ptemp=0;
       Vertex v[] = new Vertex[w*h];
       int minSeam[] = new int[w*h];
       int edgeto[][] = new int[w*h][2];
       double weight[] = new double[w*h];
       for(int i=0;i<w;i++)
       {
       v[vf]=new Vertex(i,row,vf,pic);
       edgeto[vf][0]=0;
       edgeto[vf][1]=vf;
       weight[vf]=v[vf].energy();
       ++vf;
       }
       while(row<h-1)
           {   
              //d=0;
              c_trav=0;
              ptemp=prfc;
              for(int i=0;i<w;i++)
              {
                  int cv=ptemp+c_trav;
                  
                if(c_trav==0)
                {
                  /*if((v[cv].getx()-1)>=0)
                  {  
                    insertdata(v[cv].getx()-1,row+1,vf,cv,v,edgeto,weight);
                    d++;
                    prfc=vf;
                    ++vf;
                    // System.out.println("mai pehli wali condition hu jisme mai check hota hu bound ke liye");
                  }*/
                  
                      
                  insertdata(v[cv].getx(),row+1,vf,cv,v,edgeto,weight);
                  
                  prfc=vf;
                  ++vf;
                  
                  /*if(v[cv].getx()+1<=this.getWidth()-1)
                  {*/
                      insertdata(v[cv].getx()+1,row+1,vf,cv,v,edgeto,weight);
                      ++vf;
                  /*}*/
                  ++c_trav;
                }
                else
                {
                  //System.out.println("oye else mai aagya");
                  CompareData(cv,vf-2,v,edgeto,weight);
                  CompareData(cv,vf-1,v,edgeto,weight);
                  if(v[cv].getx()+1<this.getWidth()-1)
                  {
                    insertdata(v[cv].getx()+1,row+1,vf,cv,v,edgeto,weight);
                    ++vf;
                  }
                  ++c_trav;
                }
                  
            }
               
            ++row;  
          }
       /*for(int i=0;i<(w-1)*(h-2);i++)
       {
           System.out.println(edgeto[i][0]+" to "+edgeto[i][1]);
       }*/
          min=weight[prfc];
          for(int m=prfc;m<=prfc+w-1;m++)
          {
                 if(weight[m]<min)
                 {  
                    min=weight[m];
                    posmin=m;
                    
                 } 
              
             }
               
                 int t=posmin;
                 for(int k=this.getHeight()-1;k>=0;k--)
                 {
                     //System.out.println("i am here");
                     if(k==0)
                     {
                        // System.out.println("used condition");
                         
                         minSeam[k]=minSeam[k+1];
                       //  System.out.println(minSeam[0]);
                     }
                     else
                     {
                     minSeam[k]=v[t].getx();
                     t=edgeto[t][0];
                     }
                 }
//System.out.println("print starts");
//                 for(int k=0;k<h;k++)
//                 {
//                     
//                     System.out.println(k+" "+minSeam[k]);
//                     
//                 }
//  
             
     
    return minSeam;  
   }
   // sequence of indices for vertical   seam in current picture
   public    void removeHorizontalSeam()
   {
   
       // System.out.println("--------------------------------------");
       //int ms[]=this.findVerticalSeam();
       
//      System.out.println(count);
       int a[]=this.findHorizontalSeam();
//       int i,j;
//       SCUtility obj = new SCUtility();
//      double e[][]= obj.toEnergyMatrix(this);
//     
//      for( i=0;i<this.getHeight();i++)
//       {
//           for( j=a[i];j<this.getWidth()-count;j++)
//           {
//               e[j][i]=e[j+1][i];
//              
//           }
//           e[j][i]=2000000;
//       }
//      
//          for( i=0;i<this.getHeight();i++)
//       {
//           for( j=a[i];j<this.getWidth()-count;j++)
//           {
//            System.out.print(e[j][i]+" ");
//            System.out.println();
//            
//           }
//          
//       }
//       
//     pic=obj.toEnergyPicture(this);
//      
//     pic.show();
        int i,j;
      int w=pic.width();
      int h=pic.height();
      
       Color col[][]=new Color[h][w];
Picture alt=new Picture(w,h-1);

System.out.println(h);
for(i=0;i<h;i++)
       {
           for(j=0;j<w;j++)
           
                       col[i][j]=pic.get(j, i);
                         
                   
       }
       
        
       for(i=0;i<w;i++)
       {
           for(j=a[i];j<h-1;j++)
                      col[j][i]=pic.get(i, j+1);
                         
                   
       }
       
          
          for(i=0;i<w;i++)
       {
           for(j=0;j<h-1;j++)
           alt.set(i,j , col[j][i]);
                  
       }
       
       
      pic=alt;
    //  pic.show();
       
  
   }
// remove horizontal seam from current picture
   public    void removeVerticalSeam()   
   {
      // System.out.println("--------------------------------------");
       //int ms[]=this.findVerticalSeam();
       
//      System.out.println(count);
       int a[]=this.findVerticalSeam();
//       int i,j;
//       SCUtility obj = new SCUtility();
//      double e[][]= obj.toEnergyMatrix(this);
//     
//      for( i=0;i<this.getHeight();i++)
//       {
//           for( j=a[i];j<this.getWidth()-count;j++)
//           {
//               e[j][i]=e[j+1][i];
//              
//           }
//           e[j][i]=2000000;
//       }
//      
//          for( i=0;i<this.getHeight();i++)
//       {
//           for( j=a[i];j<this.getWidth()-count;j++)
//           {
//            System.out.print(e[j][i]+" ");
//            System.out.println();
//            
//           }
//          
//       }
//       
//     pic=obj.toEnergyPicture(this);
//      
//     pic.show();
        int i,j;
      int w=pic.width();
      int h=pic.height();
      
       Color col[][]=new Color[h][w];
Picture alt=new Picture(w-1,h);

System.out.println(w);
for(i=0;i<h;i++)
       {
           for(j=0;j<w;j++)
           
                       col[i][j]=pic.get(j, i);
                         
                   
       }
       
        
       for(i=0;i<h;i++)
       {
           for(j=a[i];j<w-1;j++)
                      col[i][j]=pic.get(j+1, i);
                         
                   
       }
       
          
          for(i=0;i<h;i++)
       {
           for(j=0;j<w-1;j++)
           alt.set(j,i , col[i][j]);
                  
       }
       
       
      pic=alt;
     // pic.show();
       
   }
}