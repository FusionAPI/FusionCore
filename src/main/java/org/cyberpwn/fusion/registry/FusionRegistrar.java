package org.cyberpwn.fusion.registry;

import java.util.HashMap;
import java.util.Map;

import org.cyberpwn.fusionapi.registry.Registrant;
import org.cyberpwn.fusionapi.registry.Registrar;
import org.cyberpwn.fusionapi.registry.RegistryType;
import org.cyberpwn.fusionapi.type.FBlock;
import org.cyberpwn.fusionapi.type.FItem;
import org.cyberpwn.fusionapi.type.FModel;
import org.cyberpwn.fusionapi.type.FShader;
import org.cyberpwn.fusionapi.type.FSound;
import org.cyberpwn.fusionapi.type.FTexture;

public class FusionRegistrar implements Registrar 
{
	private String source;
	private Map<RegistryType, Registrant<?>> registers;

	public FusionRegistrar(String source) 
	{
		this.source = source;
		registers = new HashMap<RegistryType, Registrant<?>>();

		for (RegistryType i : RegistryType.values()) 
		{
			Registrant<?> register = null;

			switch (i) 
			{
				case ITEM:
					register = new FusionRegistrant<FItem>(source, i);
				case BLOCK:
					register = new FusionRegistrant<FBlock>(source, i);
				case MODEL:
					register = new FusionRegistrant<FModel>(source, i);
				case SHADER:
					register = new FusionRegistrant<FShader>(source, i);
				case SOUND:
					register = new FusionRegistrant<FSound>(source, i);
				case TEXTURE:
					register = new FusionRegistrant<FTexture>(source, i);
			}

			registers.put(i, register);
		}
	}

	@Override
	public String getSource() 
	{
		return source;
	}

	@Override
	public Registrant<?> getRegistrant(RegistryType type)
	{
		return registers.get(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FItem> getItemRegistrant()
	{
		return (Registrant<FItem>) getRegistrant(RegistryType.ITEM);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FBlock> getBlockRegistrant()
	{
		return (Registrant<FBlock>) getRegistrant(RegistryType.BLOCK);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FSound> getSoundRegistrant() 
	{
		return (Registrant<FSound>) getRegistrant(RegistryType.SOUND);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FTexture> getTextureRegistrant() 
	{
		return (Registrant<FTexture>) getRegistrant(RegistryType.TEXTURE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FModel> getModelRegistrant() 
	{
		return (Registrant<FModel>) getRegistrant(RegistryType.MODEL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FShader> getShaderRegistrant() 
	{
		return (Registrant<FShader>) getRegistrant(RegistryType.SHADER);
	}
}
