package it.dsestili.jhashcode.core;

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

@SuppressWarnings("serial")
public class ScanProgressEvent extends ProgressEvent
{
	
	public ScanProgressEvent(Object source, int pCent, long current, long tot, int filesFound, int directoriesFound, long totalSize, int charIndex)
	{
		super(source, pCent, current, tot);
		
		this.filesFound = filesFound;
		this.directoriesFound = directoriesFound;
		this.totalSize = totalSize;
		this.charIndex = charIndex;
	}

	@Override
	public String toString() 
	{
		Object[] messageArguments = {
			    new Integer(filesFound),
			    new Integer(directoriesFound),
			    new Integer(pCent),
			    new Long(current),
			    new Long(tot),
			    Utils.getFriendlySize(totalSize),
			    charArray[charIndex]
			};
		
		return Utils.getInternationalizedString(messageArguments, "workerThread.scanProgressEvent");
	}

}
