#include "chat.h"
#include "client.h"

JNIEXPORT void JNICALL Java_examples_Chat_listUsr
  (JNIEnv *env, jobject obj, jint ns)
{
    list(ns);
}

JNIEXPORT jstring JNICALL Java_examples_Chat_waitForServer
  (JNIEnv *env, jobject obj, jint ns)
{
    char buf[BUF];
    
    memset(buf, 0, sizeof(buf));
    clientd(ns, buf);
    printf("in chat_middle: %s\n", buf);
    return (*env)->NewStringUTF(env, buf);
}