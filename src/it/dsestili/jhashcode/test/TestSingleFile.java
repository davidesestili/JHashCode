package it.dsestili.jhashcode.test;

/*
JHashCode a simple hash code generator
Copyright (C) 2013-2016 Davide Sestili

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.io.File;
import java.text.DecimalFormat;

//import java.util.Timer;
//import java.util.TimerTask;
import it.dsestili.jhashcode.core.Core;
import it.dsestili.jhashcode.core.IProgressListener;
import it.dsestili.jhashcode.core.ProgressEvent;
import it.dsestili.jhashcode.gui.MainWindow;
import java.security.MessageDigest;

public class TestSingleFile implements IProgressListener 
{

	private void test(String fileName)
	{
		/*
		String[] algorithms = Core.getAlgorithms();
		
		for(String algorithm : algorithms)
		{
			System.out.println(algorithm);
		}
		*/
		
		try
		{
			MainWindow.setItalianLocale();
			
			if(fileName == null || fileName.isEmpty())
			{
				System.out.println("Blank file name!");
				return;
			}
			
			System.out.println("File name: " + fileName);
			File file = new File(fileName);
			
			if(!file.isFile())
			{
				System.out.println("Is not a file!");
				return;
			}
			
			if(!file.exists())
			{
				System.out.println("File does not exist!");
				return;
			}
			
                        String algorithm = "SHA-224";
                        MessageDigest.getInstance(algorithm);
                        System.out.println("Algorithm " + algorithm + " exist!");
                        
			final Core core = new Core(file, algorithm);
			core.addIProgressListener(this);

			/*
			Timer timer = new Timer(true);
			timer.schedule(new TimerTask() {
				@Override
				public void run() 
				{
					core.interrupt();
				}
			}, 10000);
			*/

			long start = System.currentTimeMillis();
			String hash = core.generateHash();
			long elapsed = System.currentTimeMillis() - start;
			
			System.out.println("Hash: " + hash.toUpperCase());
			
			DecimalFormat myFormatter = new DecimalFormat("###.##");
			double seconds = (double)elapsed / 1000.0;
			System.out.println("Elapsed time: " + myFormatter.format(seconds) + " seconds");
		}
		catch(InterruptedException e)
		{
			System.out.println("Operation canceled!");
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
            new TestSingleFile().test("/home/dsestili/Scaricati/jdk-8u111-nb-8_2-linux-x64.sh");
            
            /*
            if(args.length > 0)
		{
			new TestSingleFile().test(args[0]);
		}
		else
		{
			System.out.println("Parameter missing!");
		}
*/
	}

	public void progressEvent(ProgressEvent event) 
	{
		System.out.println(event);
	}
}
