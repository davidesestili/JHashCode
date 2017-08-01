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

package it.dsestili.jhashcode.core;

import it.dsestili.jhashcode.gui.GUIFactory;
import it.dsestili.jhashcode.gui.MainWindow;
import java.util.TimerTask;

public class ElapsedTimerTask extends TimerTask 
{
	private long time = -1000;
	
	@Override
	public synchronized void run() 
	{
		time += WorkerThread.SECOND;

		final String elapsedTime = " - " + WorkerThread.elapsedTime + " " + Utils.getElapsedTime(time, false);
		
		final MainWindow frame = GUIFactory.MainWindowFactory.getInstance();

		WorkerThread.setTitleAndTime(frame, elapsedTime);
	}

}
