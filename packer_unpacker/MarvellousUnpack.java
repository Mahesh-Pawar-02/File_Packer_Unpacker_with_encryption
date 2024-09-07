//MarvellousUnpack.java

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MarvellousUnpack
{
    FileOutputStream outstream = null;

    public MarvellousUnpack(String src) throws Exception
    {
        unpack(src);
    }

    public void unpack(String filePath) throws Exception
    {
        try{
            FileInputStream instream = new FileInputStream(filePath);

            byte Header = new byte[100];
            instream.read(Magic,0,Magic.length);

            String Magicstr = new String(Magic);

            if(!Magicstr.equals("Marvellous11"))
            {
                throw new InvalidFileException("Invalid packed file format");
            }

            while((length = instream.read(header,0,100))>0)
            {                
                String str = new String(Header);

                String ext = str.substring(str.lastIndexOf("/"));
                ext = ext.substring(1);

                String[] words = ext.split("\\s");

                String filename = words[0];
                
                int size = Integer.parseInt(words[1]);

                byte arr[] = new byte[size];

                instream.read(arr,0,size);

                FileOutputStream fout = new FileOutputStream(filename);
                fout.write(arr,0,size);
            }
        }
        catch(InvalidFileException obj)
        {
            throw new InvalidFileException("Invalid packed file format");
        }
        catch(Exception e)
        {}
    }
}
