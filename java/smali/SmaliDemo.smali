.class public Lcom/anyuaning/SmaliDemo;

.super Ljava/lang/Object;
.source "SmaliDemo.java"

# direct methods
.method public constructor <init>()V

  .registers 1

  .prologue

  invoke-direct {p0}, Ljava/lang/Object;-><init>()V

  return-void

.end method

.method public static main([Ljava/lang/String;)V

  .registers 3
  .param p0, "args"    # [Ljava/lang/String;

  .prologue

  sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

  const-string v1, "smali demo"

  invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

  return-void


.end method

## java -jar /opt/bin/smali-2.2.2.jar a -o demo.dex SmaliDemo.smali
## adb push demo.dex /sdcard
## adb shell dalvikvm -cp /sdcard/demo.dex com.anyuaning.SmaliDemo
