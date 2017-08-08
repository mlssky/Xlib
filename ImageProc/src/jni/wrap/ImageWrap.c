//
// Created by mengliwei on 2017/7/25.
//

#include "../MagickCore/MagickCore.h"
#include "com_xcleans_image_ImageWrapp.h"

/**
 *
 * @param env
 * @param obj
 * @param input   input file
 * @param output   out file
 * @param degress  rorate degress
 * @param newSizeW  crop size Width
 * @param newSizeH  crop size Height
 */
void handlerImage(JNIEnv *env, jobject obj, jstring input, jstring output, jdouble degress,
                  jint newSizeW, jint newSizeH);

static const char *CLASS = "com/xcleans/image/ImageWrapp";
static JNINativeMethod bitmaps_native_methods[] = {
        {"handlerImage",
                "(Ljava/lang/String;Ljava/lang/String;DII)V",
                (void *) handlerImage}
};


__attribute__((visibility("default")))
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    UNUSED(reserved);
    JNIEnv *env;

    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    jclass cls = (*env)->FindClass(env, CLASS);
    if (!cls) {
        return JNI_ERR;
    }
    int rc = (*env)->RegisterNatives(
            env,
            cls,
            bitmaps_native_methods,
            ARRAY_SIZE(bitmaps_native_methods));
    if (rc != JNI_OK) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}


/**
 *
 * @param argc
 * @param argv
 * @param inputPath  input file path
 * @param outpath   out file path
 * @param roate
 */

void handlerImage(JNIEnv *env, jobject obj, jstring input, jstring output, jdouble degress,
                  jint newSizeW, jint newSizeH) {
    UNUSED(obj);
    const char *inputPath = (*env)->GetStringUTFChars(env, input, JNI_FALSE);
    const char *outpath = (*env)->GetStringUTFChars(env, output, JNI_FALSE);
    size_t newW = newSizeW;
    size_t newH = newSizeH;
    const double roate = degress;
    LOGD("start handlerImage");

    Image *image, *images, *resize_image = NULL, *rorate_image = NULL, *thumbnails;
    //save exe info
    ExceptionInfo *exception;
    ImageInfo *image_info;

    LOGD("start MagickCoreGenesis");
    // Initialize the image info structure and read an image.
    MagickCoreGenesis((char *) NULL, MagickFalse);
    LOGD("END MagickCoreGenesis");

    exception = AcquireExceptionInfo();
    image_info = CloneImageInfo((ImageInfo *) NULL);
//    profile
    LOGD("start strcpy");
    (void) strcpy(image_info->filename, inputPath);
    LOGD("end strcpy");

    LOGD("start ReadImage");
    //LOGD(inputPath);
    images = ReadImage(image_info, exception);
    size_t orignW = images->columns;
    size_t orignH = images->rows;
    if (degress != 0) {
        if (newSizeW < 0 || newSizeH < 0) {
            newSizeW = orignW;
            newSizeH = orignH;
        }
    }
//    images->compression = LZWCompression;
//    images->colorspace = RGBColorspace;
//    images->quality = 80;

//    images->image_info->compression = LZWCompression;
//    images->image_info->colorspace = RGBColorspace;
//    images->image_info->quality = 80;
//    images->image_info->profile = NULL;

    LOGD("end ReadImage");

    if (exception->severity != UndefinedException) {
        LOGD("UndefinedException");
        CatchException(exception);
    }
    if (images == (Image *) NULL) {
        LOGD("exit");
        exit(1);
    }

    //Convert the image to a thumbnail.
    thumbnails = NewImageList();
    while ((image = RemoveFirstImageFromList(&images)) != (Image *) NULL) {

        if (roate > 0) {
            rorate_image = RotateImage(image, roate, exception);
        }
        if (newW > 0 && newH > 0) {
            if (rorate_image != (Image *) NULL) {
//                rorate_image->quality = 10;
                resize_image = ResizeImage(rorate_image, newW, newH, LanczosFilter, exception);
                DestroyImage(rorate_image);
                rorate_image = NULL;
            } else {
                resize_image = ResizeImage(image, newW, newH, LanczosFilter, exception);
            }
        }

        if (resize_image == (Image *) NULL && rorate_image == (Image *) NULL) {
            MagickError(exception->severity, exception->reason, exception->description);
        }
        if (resize_image != (Image *) NULL) {
            (void) AppendImageToList(&thumbnails, resize_image);
        } else {
            (void) AppendImageToList(&thumbnails, rorate_image);
        }
        DestroyImage(image);
    }

    //Write the image thumbnail.
    (void) strcpy(thumbnails->filename, outpath);
    WriteImage(image_info, thumbnails, exception);

    //Destroy the image thumbnail and exit.
    thumbnails = DestroyImageList(thumbnails);
    image_info = DestroyImageInfo(image_info);
    exception = DestroyExceptionInfo(exception);

    MagickCoreTerminus();

    LOGD("end handlerImage");
}



