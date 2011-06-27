#include "client.h"
#include "login.h"




JNIEXPORT jint JNICALL Java_examples_Antenna_connectInit
  (JNIEnv *env, jobject obj)
{
    return connect_init();
}

JNIEXPORT jchar JNICALL Java_examples_Antenna_login
  (JNIEnv *env, jobject obj, jint ns, jstring usrname, jstring password)
{
    const jbyte *jb_usrname;
    const jbyte *jb_password;

    jb_usrname = (*env)->GetStringUTFChars(env, usrname, NULL);
    jb_password = (*env)->GetStringUTFChars(env, password, NULL);
    return login(ns, jb_usrname, jb_password);
}
