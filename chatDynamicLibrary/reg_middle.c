#include "client.h"
#include "register.h"

JNIEXPORT jchar JNICALL Java_examples_Register_submitRegister
  (JNIEnv *env, jobject obj, jint ns, jstring usrname, jstring password)
{
    const jbyte *jb_usrname;
    const jbyte *jb_password;

    jb_usrname = (*env)->GetStringUTFChars(env, usrname, NULL);
    jb_password = (*env)->GetStringUTFChars(env, password, NULL);
    return reg(ns, jb_usrname, jb_password);
}
