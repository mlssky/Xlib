# list of supported architectures 
# https://developer.android.com/ndk/guides/application_mk.html
#APP_ABI := all
APP_ABI := armeabi
APP_PLATFORM := android-16
NDK_TOOLCHAIN_VERSION := 4.9
APP_CFLAGS:=-DNDEBUG -Os -g0 -fvisibility=hidden
# list of build-in STL's supported by NDK.
# switch to clang STL in case of using this compiler
# https://developer.android.com/ndk/guides/cpp-support.html#runtimes
APP_STL := stlport_static
