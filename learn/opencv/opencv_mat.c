#include<cv.h>
#include<highgui.h>
#include<stdio.h>
float sum(CvMat* mat)
{
    float s = 0.0f;
    int row,col;
  for(  row=0; row<mat->rows; row++ ) {
    float* ptr = mat->data.fl + row * mat->step/4;
    for( col=0; col<mat->cols; col++ ) {
      s += *ptr++;
    }
  }
  return( s );
}
int main(int argc,char* argv[])
{
    float vals[]={0.879632,-0.5,0.5,0.879632};
    CvMat rotmat;
    cvInitMatHeader(&rotmat,2,2,CV_32FC1,vals,CV_AUTOSTEP);
    printf("Initialized succeed!\n");
    float element_1_0=CV_MAT_ELEM(rotmat,float,1,0);
    printf("element_1_0:%f\n",element_1_0);

    CvMat* mat = cvCreateMat( 5, 5, CV_32FC1 );
  float element_3_2 = 7.7;
  *( (float*)CV_MAT_ELEM_PTR( *mat, 3, 2 ) ) = element_3_2;

  // below from example ch3_ex3_8.txt
  cvmSet( mat, 2, 2, 0.5000 );
  cvSetReal2D( mat, 3, 3, 0.3300 );


  printf("matrix created and accessed:\n [3,2]=%f, [2,2]=%f, [3,3]=%f\n",CV_MAT_ELEM( *mat, float, 3, 2 ),CV_MAT_ELEM( *mat, float, 2, 2 ),CV_MAT_ELEM( *mat, float, 3, 3 ));
float s=sum(mat);
printf("sum:%f\n",s);
    return 0;
}
