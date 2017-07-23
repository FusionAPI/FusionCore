package org.cyberpwn.fusion.resource;

import java.io.File;

import org.cyberpwn.fusionapi.resource.Horde;
import org.cyberpwn.fusionapi.resource.ResourceHolder;
import org.cyberpwn.fusionapi.type.FModel;
import org.cyberpwn.fusionapi.type.FShader;
import org.cyberpwn.fusionapi.type.FSound;
import org.cyberpwn.fusionapi.type.FTexture;

public class FusionResourceHolder implements ResourceHolder 
{
	private Horde<FModel> modelHorde;
	private Horde<FTexture> textureHorde;
	private Horde<FSound> soundHorde;
	private Horde<FShader> shaderHorde;
	
	public FusionResourceHolder(File holder) 
	{
		modelHorde = new ResourceHorde<FModel>(new File(holder, "model"));
		textureHorde = new ResourceHorde<FTexture>(new File(holder, "texture"));
		soundHorde = new ResourceHorde<FSound>(new File(holder, "sound"));
		shaderHorde = new ResourceHorde<FShader>(new File(holder, "shader"));
	}

	@Override
	public Horde<FModel> getModelHorde() 
	{
		return modelHorde;
	}

	@Override
	public Horde<FShader> getShaderHorde()
	{
		return shaderHorde;
	}

	@Override
	public Horde<FSound> getSoundHorde() 
	{
		return soundHorde;
	}

	@Override
	public Horde<FTexture> getTextureHorde() 
	{
		return textureHorde;
	}
}
