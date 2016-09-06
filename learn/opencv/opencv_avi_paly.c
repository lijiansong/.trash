#include<highgui.h>
int main(int argc,char** argv)
{
    cvNamedWindow("AVI play",CV_WINDOW_AUTOSIZE);
    CvCapture* capture=cvCreateFileCapture(argv[1]);
    IplImage* frame;
    while(1)
    {
        frame=cvQueryFrame(capture);
        if(!frame)
            break;
        cvShowImage("AVI play",frame);
        char c=cvWaitKey(33);
        if(c==27)
            break;
    }
    cvReleaseCapture(&capture);
    cvDestroyWindow("AVI play");
    return 0;
}
