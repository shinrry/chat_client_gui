#include "talk.h"
#include "client.h"

JNIEXPORT void JNICALL Java_examples_Talk_sendMsg
  (JNIEnv *env, jobject obj, jint ns, jstring sender, jstring target, jstring content)
{
    const jbyte *jb_target;
    const jbyte *jb_content;
    const jbyte *jb_sender;
    
    jb_target = (*env)->GetStringUTFChars(env, target, NULL);
    jb_sender = (*env)->GetStringUTFChars(env, sender, NULL);
    jb_content = (*env)->GetStringUTFChars(env, content, NULL);   
    talk(ns, jb_target, jb_sender, jb_content);
}
