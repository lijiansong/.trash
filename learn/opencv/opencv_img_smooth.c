#include<cv.h>
#include<highgui.h>
void smooth(IplImage* image)
{
    cvNamedWindow("smooth-in",CV_WINDOW_AUTOSIZE);
    cvNamedWindow("smooth-out",CV_WINDOW_AUTOSIZE);
    cvShowImage("smooth-in",image);
    //3 channels,each channel 8 bits
    IplImage* out=cvCreateImage(cvGetSize(image),IPL_DEPTH_8U,3);
    //do smoothing
    //每个像素周围3*3区域进行高斯平滑处理
    cvSmooth( image, out, CV_GAUSSIAN, 5,5,0,0 );
    cvSmooth( out, out, CV_GAUSSIAN, 5, 5,0,0);

    cvShowImage("smooth-out",out);

    cvReleaseImage(&out);
    cvWaitKey(0);
    cvDestroyWindow("smooth-in");
    cvDestroyWindow("smooth-out");
}
int main(int argc,char** argv)
{
    IplImage* img=cvLoadImage(argv[1],1);
    cvNamedWindow("Test", CV_WINDOW_AUTOSIZE );
    cvShowImage("Test", img );
    smooth( img );
    //cvWaitKey(0);
    cvReleaseImage( &img );
    cvDestroyWindow("Test");
    return 0;
}
