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

import it.dsestili.jhashcode.core.DirectoryInfo;
import it.dsestili.jhashcode.core.DirectoryScanner;
import it.dsestili.jhashcode.core.DirectoryScannerNotRecursive;
import it.dsestili.jhashcode.core.DirectoryScannerRecursive;
import it.dsestili.jhashcode.core.FolderMode;
import it.dsestili.jhashcode.core.IScanProgressListener;
import it.dsestili.jhashcode.core.ProgressEvent;
import it.dsestili.jhashcode.gui.MainWindow;

public class TestDirectoryScanner implements IScanProgressListener
{
	private void test(String directoryParam)
	{
		try
		{
			MainWindow.setSpanishLocale();
			
			checkIsEmpty(directoryParam);
			
			System.out.println("Directory: " + directoryParam);
			File directory = new File(directoryParam);

			if(!directory.exists())
			{
				throw new Exception("Directory does not exist");
			}
			
			DirectoryScanner scanner = null;
			
			FolderMode folderMode = FolderMode.SUBFOLDERS_WITH_RECURSIVE_ALGORITHM;
			
			if(folderMode == FolderMode.SUBFOLDERS_WITH_NOT_RECURSIVE_ALGORITHM)
			{
				scanner = new DirectoryScannerNotRecursive(directory, true);
			}
			else if(folderMode == FolderMode.SUBFOLDERS_WITH_RECURSIVE_ALGORITHM)
			{
				scanner = new DirectoryScannerRecursive(directory, true);
			}
			else if(folderMode == FolderMode.DO_NOT_SCAN_SUBFOLDERS)
			{
				scanner = new DirectoryScannerRecursive(directory, false);
			}
			
			scanner.addIScanProgressListener(this);
			
			DirectoryInfo di = scanner.getFiles();
			File[] files = di.getFiles();
			long totalSize = di.getTotalSize();
			
			System.out.println("Scanning completed, " + files.length + " files found, " + totalSize + " bytes (total size)");
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
	
	private void checkIsEmpty(String param) throws Exception
	{
		if(param == null || param.isEmpty())
		{
			throw new Exception("Blank parameter!");
		}
	}
	
	public static void main(String[] args) 
	{
		if(args.length == 1)
		{
			new TestDirectoryScanner().test(args[0]);
		}
		else
		{
			System.out.println("A parameter is needed!");
		}
	}

	public void scanProgressEvent(ProgressEvent event) 
	{
		System.out.println(event);
	}
}
