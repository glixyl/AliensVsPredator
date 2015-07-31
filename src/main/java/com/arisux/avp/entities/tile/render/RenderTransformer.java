package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.entities.tile.TileEntityTransformer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderTransformer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityTransformer transformer = null;

		if (tileEntity instanceof TileEntityTransformer)
		{
			transformer = (TileEntityTransformer) tileEntity;
		}

		glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);

			GL11.glTranslated(posX, posY, posZ);
			// North
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.NORTH)
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.NORTH)
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
			}

			GL11.glTranslated(0, 0, 1);

			// South
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.SOUTH)
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.SOUTH)
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
			}

			GL11.glRotatef(90, 1, 0, 0);

			// Down
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.DOWN)
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.DOWN)
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
			}

			GL11.glTranslated(0, 0, -1);

			// Up
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.UP)
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.UP)
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
			}

			GL11.glRotatef(90, 0, 1, 0);

			// West
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.WEST)
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.WEST)
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
			}

			GL11.glTranslated(0, 0, 1);

			// East
			if (transformer != null && transformer.acceptVoltageDirection == ForgeDirection.EAST)
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFF00FF00);
			}
			else if (transformer != null && transformer.acceptVoltageDirection.getOpposite() == ForgeDirection.EAST)
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFF0000FF);
			}
			else
			{
				RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
			}
		}
		glPopMatrix();
	}
}
