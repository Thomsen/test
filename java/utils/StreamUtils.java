import java.util.zip.InflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;


public class StreamUtils {

    private StreamUtils() {
    }
    
    private static final int BYTE_SIZE = 512;
    
    public static String convertInputStreamToString(InputStream inputStream) {
        byte[] bt = new byte[BYTE_SIZE];
        StringBuilder sb = new StringBuilder();
        if (null != inputStream) {
            try {
                while (inputStream.read(bt) != -1) {
                    sb.append(new String(bt, "UTF-8"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public static String convertInputStreamLineToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        if (null != inputStream) {
            BufferedReader inreader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            try {
                while ((line = inreader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                        inreader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public static File convertInputStreamToFile(InputStream inputStream) {
        byte[] bt = new byte[BYTE_SIZE];
        StringBuilder sb = new StringBuilder();
        if (null != inputStream) {
            try {
                while (inputStream.read(bt) != -1) {
                    sb.append(new String(bt, "UTF-8"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static final byte[] getByteFromFile(File file) {
        byte[] result = new byte[1024];
        if (null == file || !file.isFile()) {
            return result;
        }
		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            byte[] buffer = new byte[1024];
            fis = new FileInputStream(file);
			
            int len = fis.read(buffer, 0, buffer.length);  // 从流到字节
            while (len > 0) {
                baos.write(buffer);  // 从字节到流
                len = fis.read(buffer, 0, buffer.length);
            }
			
            result = baos.toByteArray();
            baos.flush();
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
        return result;
    }

    public static final void saveFileFromByte(byte[] data, File file) {
        if (null == data || null == file) {
            return ;
        }
        //		if (!file.exists()) {
        //			file.mkdir();
        //		}
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
    }

    public static final byte[] compressByZip(byte[] data) {
        byte[] result = null;
        if (null == data) {
            return result;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zip = null;
        try {
            zip = new ZipOutputStream(baos);
            ZipEntry entry = new ZipEntry("zip");
            entry.setSize(data.length);
            zip.putNextEntry(entry);
            zip.write(data);
            zip.closeEntry(); // need
			
            result = baos.toByteArray();
            baos.flush();
			
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (null != zip) {
                try {
					
                    zip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static final byte[] decompressByZip(byte[] data) {
        byte[] result = null;
        if (null == data) {
            return result;
        }
		
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(bais);
            if (null != zis.getNextEntry()) {  // while
                byte[] buffer = new byte[1024];
                int len = zis.read(buffer);
                while (len > 0) {
                    baos.write(buffer, 0, len);
                    len = zis.read(buffer);
                }
            }
			
            result = baos.toByteArray();
            baos.flush();
			
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != zis) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
        return result;
    }

    public static final byte[] compressByDeflater(byte[] data) {
        byte[] result = null;
        if (null == data) {
            return result;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(baos);
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        DeflaterOutputStream zos = null;
        try {
            zos = new DeflaterOutputStream(os);
            DataOutputStream objOut = new DataOutputStream(zos);
            objOut.write(data);
            objOut.flush();
            objOut.close();
			
            result = baos.toByteArray();
            baos.flush();
			
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                os.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
        return result;
    }

    public static final byte[] decompressByInflater(byte[] data) {
        byte[] result = null;
        if (null == data) {
            return result;
        }
		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        InflaterInputStream iis = null;
        try {
            iis = new InflaterInputStream(bais);  // nowrap false
            byte[] buffer = new byte[1024];
            int len = iis.read(buffer);  // invalid stored block lengths
            while (len > 0) {
                baos.write(buffer);
                len = iis.read(buffer);
            }
			
            //			int b = -1;
            //			while ((b = iis.read()) != -1) {
            //				baos.write(b);
            //			}
			
            result = baos.toByteArray();
            baos.flush();
			
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != iis) {
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
        return result;
    }
}

