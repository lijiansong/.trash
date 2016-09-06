/*                                  _   _ ____  _
     2   *  Project                     ___| | | |  _ \| |
     3   *                             / __| | | | |_) | |
     4   *                            | (__| |_| |  _ <| |___
     5   *                             \___|\___/|_| \_\_____|
     6   *
     7   * $Id: simple.c,v 1.6 2004/08/23 14:22:52 bagder Exp $
     8   */
#include <stdio.h>
#include "curl/curl.h"
int main(void)
{
    CURL *curl;
    CURLcode res;
    
    curl = curl_easy_init();
    if(curl) {
    curl_easy_setopt(curl, CURLOPT_URL, "www.sina.com");
    res = curl_easy_perform(curl);
    /* always cleanup */
    curl_easy_cleanup(curl);
   }
  return 0;
}

