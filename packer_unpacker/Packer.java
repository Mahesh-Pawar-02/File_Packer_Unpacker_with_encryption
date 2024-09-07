import java.util.*;
import java.io.*;

class Packer
{
    public static void main(String arg[])
    {
        Scanner sobj = new Scanner(System.in);
        boolean bRet = false;
        int iRet = 0;
        byte Buffer[] = new byte[1024];
        int PackCount = 0;

        System.out.println("-------------- Marvellous Packer Unpacker --------------");

        System.out.println("Packing Activity of the application is started...");


        System.out.println("Enter the name of folder which contains the files that you want to pack : ");
        String FolderName = sobj.nextLine();

        File fobj = new File(FolderName);
        String Header = null;

        System.out.println("Enter the name of packed file that you want to create : ");
        String PackFile = sobj.nextLine();


        try 
        { 
            File Packobj = new File(PackFile);
            bRet = Packobj.createNewFile();

            if(bRet == false)
            {
                System.out.println("Unable to create packed file");
                return;
            }
            
            System.out.println("Packed file gets successfully created in your current directory");

            FileOutputStream outobj = new FileOutputStream(Packobj);

            bRet = fobj.isDirectory();
            if(bRet == true)
            {
                File list[] = fobj.listFiles();
            
                System.out.println("Total number of files found in the directory are : "+list.length);

                for(int i = 0; i < list.length;i++)
                {
                    if((list[i].getName()).endsWith(".txt"))
                    {
                        Header = list[i].getName() + " " + list[i].length();
                        for(int j = Header.length(); j < 100; j++)
                        {
                            Header = Header + " ";
                        }
                        
                        byte bHeader[] = Header.getBytes();
                        outobj.write(bHeader,0,bHeader.length);

                        FileInputStream inobj = new FileInputStream(list[i]);

                        //Loop to write the data
                        while((iRet = inobj.read(Buffer)) != -1)
                        {
                            outobj.write(Buffer,0,iRet);
                        }

                        System.out.println("File successfully packed with name : "+list[i].getName());

                        inobj.close();
                        PackCount++;
                    }
                }

                System.out.println("-------------- Packing Summary --------------");
                System.out.println("Total number of files scanned : "+list.length);
                System.out.println("Total number of files packes : "+PackCount);

                System.out.println("Thankyou for using Marvellous Packer Unpacker...");

            }
        }
        catch(Exception iobj)
        {
            System.out.println("Exception occured : "+iobj);
        }
    }
}