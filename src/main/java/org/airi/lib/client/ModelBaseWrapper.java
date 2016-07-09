package com.arisux.airi.lib.client;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class ModelBaseWrapper extends ModelBase
{
    
    public ModelBaseWrapper()
    {
        super();
    }

    public static interface IRenderObject
    {
        public Object getObject();
    }

    @SideOnly(Side.CLIENT)
    public static class RenderObject implements IRenderObject
    {
        public Object object;
        public float swingProgress;
        public float swingProgressPrev;
        public float idleProgress;
        public float headYaw;
        public float headPitch;

        public RenderObject(Object[] renderObjects)
        {
            this.object = renderObjects[0];

            if (renderObjects.length > 1)
            {
                this.swingProgress = (Float) renderObjects[1];
                this.swingProgressPrev = (Float) renderObjects[2];
                this.idleProgress = (Float) renderObjects[3];
                this.headYaw = (Float) renderObjects[4];
                this.headPitch = (Float) renderObjects[5];
            }
        }

        @Override
        public Object getObject()
        {
            return this.object;
        }

        public TileEntity getTileEntity()
        {
            return (TileEntity) this.object;
        }

        public Entity getEntity()
        {
            return (Entity) this.object;
        }
    }

    /**
     * Set the width and height of this ModelBaseExtension's texture.
     * 
     * @param textureWidth - The texture width in pixels
     * @param textureHeight - The texture height in pixels
     */
    public void setTextureDimensions(int textureWidth, int textureHeight)
    {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    /**
     * Set the rotation angles of the specified ModelRenderer instance.
     */
    public void setRotation(ModelRenderer model, float rotateAngleX, float rotateAngleY, float rotateAngleZ)
    {
        model.rotateAngleX = rotateAngleX;
        model.rotateAngleY = rotateAngleY;
        model.rotateAngleZ = rotateAngleZ;
    }

    public static void draw(ModelRenderer modelRenderer)
    {
        modelRenderer.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
    
    public static void draw(ModelRenderer[] group)
    {
        for (ModelRenderer child : group)
        {
            draw(child);
        }
    }

    /**
     * Render this model statically. An empty render object will be passed to rest of the model.
     */
    public void render()
    {
        this.render(null);
    }

    /**
     * Render this model dynamically by passing it a render object.
     * @param o - The render object. Passing a TileEntity or Entity will result in the automatic creation of a render object.
     * Passing a render object will result in the object being directly passed to the rest of the model. This option is much more dynamic.
     */
    public void render(Object o)
    {
        if (o == null)
        {
            this.render(new RenderObject(new Object[] { null, 0F, 0F, 0F, 0F, 0F }), RenderUtil.DEFAULT_BOX_TRANSLATION);
            return;
        }
        
        if (o instanceof IRenderObject)
        {
            this.render((IRenderObject) o, RenderUtil.DEFAULT_BOX_TRANSLATION);
            return;
        }

        if (o instanceof TileEntity)
        {
            this.render(new RenderObject(new Object[] { o }), RenderUtil.DEFAULT_BOX_TRANSLATION);
            return;
        }
        
        if (o instanceof EntityLivingBase)
        {
            EntityLivingBase entityLiving = (EntityLivingBase) o;
            float renderPartialTicks = AccessWrapper.getRenderPartialTicks();
            float yawOffset = RenderUtil.interpolateRotation(entityLiving.prevRenderYawOffset, entityLiving.renderYawOffset, renderPartialTicks);
            float yawHead = RenderUtil.interpolateRotation(entityLiving.prevRotationYawHead, entityLiving.rotationYawHead, renderPartialTicks);
            float swingProgress = (entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - renderPartialTicks));
            float swingProgressPrevious = (entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * renderPartialTicks);
            float idleProgress = (entityLiving.ticksExisted + renderPartialTicks);
            float headRotateAngleY = (yawHead - yawOffset);
            float headRotationPitch = (entityLiving.prevRotationPitch + (entityLiving.rotationPitch - entityLiving.prevRotationPitch) * renderPartialTicks);
            
            this.render(new RenderObject(new Object[] { o, swingProgress, swingProgressPrevious, idleProgress, headRotateAngleY, headRotationPitch }), RenderUtil.DEFAULT_BOX_TRANSLATION);
            return;
        }

        if (o instanceof Entity)
        {
            this.render(new RenderObject(new Object[] { o, 0F, 0F, 0F, 0F, 0F }), RenderUtil.DEFAULT_BOX_TRANSLATION);
            return;
        }
    }

    /** 
     * The base render method. Renders all of the boxes stored in the boxList. This is where the rendering is actually done.
     * 
     * @param renderObject - The render object used to pass rendering arguments to the rest of the model during 
     * rendering. Allows for dynamically rendered models.
     * @param boxTranslation - The box translation offset. Default value is 0.0625F
     **/
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        for (Object o : this.boxList)
        {
            ModelRenderer box = (ModelRenderer) o;
            box.render(boxTranslation);
        }
    }

    /**
     * The entity render method from ModelBase with correct parameter mappings. Calls the base render method.
     * 
     * @param entity - The Entity instance being rendered.
     * @param swingProgress - The arm swing progress of the Entity being rendered.
     * @param swingProgressPrev - The previous tick's arm swing progress of the Entity being rendered.
     * @param idleProgress - The idle arm swing progress of the Entity being rendered.
     * @param headYaw - The head rotation yaw of the Entity being rendered.
     * @param headPitch - The head rotation pitch of the Entity being rendered.
     * @param boxTranslation - The box translation offset. Default value is 0.0625F
     */
    @Deprecated
    @Override
    public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
    {
        this.render(new RenderObject(new Object[] { entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch }));
    }

    /**
     * The standard setRotationAngles method from ModelBase with correct parameter mappings. Calls the superclass method.
     * 
     * @param swingProgress - The arm swing progress of the Entity being rendered.
     * @param swingProgressPrev - The previous tick's arm swing progress of the Entity being rendered.
     * @param idleProgress - The idle arm swing progress of the Entity being rendered.
     * @param headYaw - The head rotation yaw of the Entity being rendered.
     * @param headPitch - The head rotation pitch of the Entity being rendered.
     * @param boxTranslation - The box translation offset. Default value is 0.0625F
     * @param entity - The Entity instance being rendered.
     */
    @Override
    public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
    {
        super.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
    }

    /**
    * The standard setLivingAnimations method from ModelBase with correct parameter mappings. Calls the superclass method.
    * 
    * @param entityLiving - The EntityLiving instance currently being rendered.
    * @param swingProgress - The arm swing progress of the Entity being rendered.
    * @param swingProgressPrev - The previous tick's arm swing progress of the Entity being rendered.
    * @param renderPartialTicks - Render partial ticks
    */
    @Override
    public void setLivingAnimations(EntityLivingBase entityLiving, float swingProgress, float swingProgressPrev, float renderPartialTicks)
    {
        super.setLivingAnimations(entityLiving, swingProgress, swingProgressPrev, renderPartialTicks);
    }
    
    /**
     * Creates an array or group of ModelRenderers.
     * 
     * @param children - The ModelRenderer instances we're adding to this group.
     * @return The array or group created.
     */
    public static ModelRenderer[] group(ModelRenderer ... children)
    {
        return children;
    }
    
    /**
     * Constructs a standard ModelBase instance from the specified class.
     * 
     * @param modelClass - A class extending ModelBase which will be instantaniated. 
     * @return Instance of the class specified in the modelClass parameter.
     */
    public static ModelBase createModelBase(Class<? extends ModelBase> modelClass)
    {
        try
        {
            return (modelClass.getConstructor()).newInstance(new Object[] {});
        }
        catch (Exception e)
        {
            AIRI.logger.bug("Error creating new model instance.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Constructs a ModelBaseExtension instance from the specified class.
     * 
     * @param modelClass - A class extending ModelBaseExtension which will be instantaniated. 
     * @return Instance of the class specified in the modelClass parameter.
     */
    public static ModelBaseWrapper createExtendedModelBase(Class<? extends ModelBaseWrapper> modelClass)
    {
        try
        {
            return (modelClass.getConstructor()).newInstance(new Object[] {});
        }
        catch (Exception e)
        {
            AIRI.logger.bug("Error creating new model instance.");
            e.printStackTrace();
        }

        return null;
    }

    public float getIdleProgress(Entity entity, float renderPartialTicks)
    {
        return ((float) entity.ticksExisted + renderPartialTicks);
    }
}
