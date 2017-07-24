package org.cyberpwn.fusion.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cyberpwn.fusionapi.Fusion;
import org.cyberpwn.fusionapi.resource.Horde;
import org.cyberpwn.fusionapi.type.FResource;

public class ResourceHorde<T extends FResource> implements Horde<T> 
{
	private File resourceHold;
	private Map<String, T> horde;

	public ResourceHorde(File resourceHold) 
	{
		this.resourceHold = resourceHold;
	}

	@Override
	public File getHordeLocation() 
	{
		return resourceHold;
	}

	@Override
	public void addResource(String id, T t) 
	{
		horde.put(id, t);
	}

	@Override
	public T getResource(String id) 
	{
		return horde.get(id);
	}

	@Override
	public InputStream openStream(String id) throws IOException 
	{
		T resource = getResource(id);
		String path = "/" + resource;

		return Fusion.class.getResourceAsStream(path);
	}

	@Override
	public void writeToFile(String id, File f) throws IOException 
	{
		InputStream is = openStream(id);
		FileOutputStream fos = new FileOutputStream(f);

		byte[] buffer = new byte[1024];
		int bytesRead;
		
		while ((bytesRead = is.read(buffer)) != -1) 
		{
			fos.write(buffer, 0, bytesRead);
		}
		
		fos.close();
	}

	@Override
	public List<String> getIds() 
	{
		return new ArrayList<String>(horde.keySet());
	}
}
