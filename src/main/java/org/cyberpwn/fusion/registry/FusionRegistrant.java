package org.cyberpwn.fusion.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cyberpwn.fusionapi.registry.Registrant;
import org.cyberpwn.fusionapi.registry.RegistryType;

public class FusionRegistrant<O> implements Registrant<O>
{
	private String source;
	private RegistryType type;
	private Map<String, O> registry;
	
	public FusionRegistrant(String source, RegistryType type)
	{
		this.source = source;
		this.registry = new HashMap<String, O>();
		this.type = type;
	}
	
	@Override
	public String getSource()
	{
		return source;
	}
	
	@Override
	public void register(String id, O object)
	{
		registry.put(id, object);
	}
	
	@Override
	public boolean isRegistered(String id)
	{
		return registry.containsKey(id);
	}
	
	@Override
	public O get(String id)
	{
		return registry.get(id);
	}
	
	@Override
	public String get(O object)
	{
		for(String i : getIds())
		{
			if(get(i).equals(object))
			{
				return i;
			}
		}
		
		return null;
	}
	
	@Override
	public List<O> getObjects()
	{
		List<O> l = new ArrayList<O>();
		
		for(String i : getIds())
		{
			l.add(get(i));
		}
		
		return l;
	}
	
	@Override
	public List<String> getIds()
	{
		return new ArrayList<String>(registry.keySet());
	}
	
	@Override
	public RegistryType getType()
	{
		return type;
	}
}
