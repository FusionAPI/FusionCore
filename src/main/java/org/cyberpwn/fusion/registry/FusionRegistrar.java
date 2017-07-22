package org.cyberpwn.fusion.registry;

import java.util.HashMap;
import java.util.Map;
import org.cyberpwn.fusionapi.registry.Registrant;
import org.cyberpwn.fusionapi.registry.Registrar;
import org.cyberpwn.fusionapi.registry.RegistryType;
import org.cyberpwn.fusionapi.type.FMaterial;
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
		
		for(RegistryType i : RegistryType.values())
		{
			Registrant<?> register = null;
			
			switch(i)
			{
				case MATERIAL:
					register = new FusionRegistrant<FMaterial>(source, i);
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
	public Registrant<FMaterial> getMaterialRegistrant()
	{
		return (Registrant<FMaterial>) getRegistrant(RegistryType.MATERIAL);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FSound> getSoundRegistrant()
	{
		return (Registrant<FSound>) getRegistrant(RegistryType.MATERIAL);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FTexture> getTextureRegistrant()
	{
		return (Registrant<FTexture>) getRegistrant(RegistryType.MATERIAL);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FModel> getModelRegistrant()
	{
		return (Registrant<FModel>) getRegistrant(RegistryType.MATERIAL);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Registrant<FShader> getShaderRegistrant()
	{
		return (Registrant<FShader>) getRegistrant(RegistryType.MATERIAL);
	}
}
