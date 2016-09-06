#include<cv.h>
#include<highgui.h>
//IplImage* doPyrDown(IplImage* in,int filter/* = IPL_GAUSSIAN_5x5*/)
/*{
    // Best to make sure input image is divisible by two.
    //
    assert( in->width%2 == 0 && in->height%2 == 0 );

    IplImage* out = cvCreateImage(
        cvSize( in->width/2, in->height/2 ),
        in->depth,
        in->nChannels
    );
    cvPyrDown( in, out,CV_GAUSSIAN_5x5 );
    return( out );
};

int main( int argc, char** argv )
{
  IplImage* img = cvLoadImage( argv[1] ,1);
  IplImage* img2 = cvCreateImage( cvSize( img->width/2,img->height/2 ), img->depth, img->nChannels);
  cvNamedWindow("Example1", CV_WINDOW_AUTOSIZE );
  cvNamedWindow("Example2", CV_WINDOW_AUTOSIZE );
  cvShowImage("Example1", img );
  img2 = doPyrDown( img ,IPL_GAUSSIAN_5x5);
  cvShowImage("Example2", img2 );
  cvWaitKey(0);
  cvReleaseImage( &img );
  cvReleaseImage( &img2 );
  cvDestroyWindow("Example1");
  cvDestroyWindow("Example2");
}*/
IplImage* doCanny(
    IplImage* in,
    double    lowThresh,
    double    highThresh,
    double    aperture)
{
    if (in->nChannels != 1)
        return(0); // Canny only handles gray scale images
    IplImage* out = cvCreateImage(
        cvGetSize( in ),
        in->depth, //IPL_DEPTH_8U,
        1);
    cvCanny( in, out, lowThresh, highThresh, aperture );
    return( out );
};

int main( int argc, char** argv )
{
  IplImage* img_rgb = cvLoadImage( argv[1],1 );
  IplImage* img_gry = cvCreateImage( cvSize( img_rgb->width,img_rgb->height ), img_rgb->depth, 1);
  cvCvtColor(img_rgb, img_gry ,CV_BGR2GRAY);
  cvNamedWindow("Example Gray", CV_WINDOW_AUTOSIZE );
  cvNamedWindow("Example Canny", CV_WINDOW_AUTOSIZE );
  cvShowImage("Example Gray", img_gry );
  IplImage* img_cny = doCanny( img_gry, 10, 100, 3 );
  cvShowImage("Example Canny", img_cny );
  cvWaitKey(0);
  cvReleaseImage( &img_rgb);
  cvReleaseImage( &img_gry);
  cvReleaseImage( &img_cny);
  cvDestroyWindow("Example Gray");
  cvDestroyWindow("Example Canny");
}


