#include "simplex_java_Matrica.h"
#include <stdio.h>

JNIEXPORT jobject JNICALL Java_simplex_1java_Matrica_GJT
  (JNIEnv *env, jobject javaobj, jobject mat1, jint r, jint s){

    //klase i metode
    jclass jcArrayList = env->FindClass("java/util/ArrayList");
    jclass jcDouble = env->FindClass("java/lang/Double");
    jmethodID arrGetId = env->GetMethodID(jcArrayList, "get", "(I)Ljava/lang/Object;");
    jmethodID arrSizeId = env->GetMethodID(jcArrayList, "size", "()I");
    jmethodID convertDoubleId = env->GetMethodID(jcDouble, "doubleValue", "()D");
    jmethodID initDoubleId = env->GetMethodID(jcDouble, "<init>", "(D)V");
    jmethodID arrSetId = env->GetMethodID(jcArrayList, "set", "(ILjava/lang/Object;)Ljava/lang/Object;");
    jmethodID initArrayListId = env->GetMethodID(jcArrayList, "<init>", "()V");
    jmethodID arrAddId = env->GetMethodID(jcArrayList, "add", "(Ljava/lang/Object;)Z");
    
    //m & n
    int m = (int) env->CallIntMethod(mat1, arrSizeId);
    jobject PrviRed1 = env->CallObjectMethod(mat1, arrGetId, 0);
    int n = (int) env->CallIntMethod(PrviRed1, arrSizeId);

    //nova matrica
    jobject mat = env->NewObject(jcArrayList, initArrayListId);

    //copy u novu matricu
    jboolean b;
    int i, j;
    for(i = 0; i < m; i++){
        jobject PomRed = env->NewObject(jcArrayList, initArrayListId);
        for(j = 0; j < n; j++)
            b = env->CallBooleanMethod(PomRed, arrAddId, env->CallObjectMethod(env->CallObjectMethod(mat1, arrGetId, i), arrGetId, j));
        b = env->CallBooleanMethod(mat, arrAddId, PomRed);
    }

    //prvi i kljucni red
    jobject PrviRed = env->CallObjectMethod(mat, arrGetId, 0);
    jobject GlavniRed = env->CallObjectMethod(mat, arrGetId, r);
    //kljucni element
    double p = (double) env->CallDoubleMethod(env->CallObjectMethod(GlavniRed, arrGetId, s), convertDoubleId);

    double x1, x2, y, x;
    jobject Red, NewD, NewO;
    for(i = 1; i < m; i++){
        if(i != r){
            Red = env->CallObjectMethod(mat, arrGetId, i);
            x2 = (double) env->CallDoubleMethod(env->CallObjectMethod(Red, arrGetId, s), convertDoubleId);
            for(j = 1; j < n; j++){
                if(j != s){
                    x1 = (double) env->CallDoubleMethod(env->CallObjectMethod(Red, arrGetId, j), convertDoubleId);
                    y = (double) env->CallDoubleMethod(env->CallObjectMethod(GlavniRed, arrGetId, j), convertDoubleId);
                    x = (x1*p-x2*y)/p;
                    NewD = env->NewObject(jcDouble, initDoubleId, x);
                    NewO = env->CallObjectMethod(Red, arrSetId, j, NewD);
                }
            }
            x = x2/p;
            NewD = env->NewObject(jcDouble, initDoubleId, x);
            NewO = env->CallObjectMethod(Red, arrSetId, s, NewD);
        }
    }
    //glavni red (red r)
    for(j = 1; j < n; j++){
        if(j != s){
            y = (double) env->CallDoubleMethod(env->CallObjectMethod(GlavniRed, arrGetId, j), convertDoubleId);
            x = -y/p;
            NewD = env->NewObject(jcDouble, initDoubleId, x);
            NewO = env->CallObjectMethod(GlavniRed, arrSetId, j, NewD);
        }
    }
    NewD = env->NewObject(jcDouble, initDoubleId, 1/p);
    NewO = env->CallObjectMethod(GlavniRed, arrSetId, s, NewD);

    //zamjena indeksa
    double e = (double) env->CallDoubleMethod(env->CallObjectMethod(PrviRed, arrGetId, s), convertDoubleId);
    double a = (double) env->CallDoubleMethod(env->CallObjectMethod(GlavniRed, arrGetId, 0), convertDoubleId);
    NewD = env->NewObject(jcDouble, initDoubleId, a);
    NewO = env->CallObjectMethod(PrviRed, arrSetId, s, NewD);
    NewD = env->NewObject(jcDouble, initDoubleId, e);
    NewO = env->CallObjectMethod(GlavniRed, arrSetId, 0, NewD);

    //return izmjenjnju novu matricu
    return mat;
}