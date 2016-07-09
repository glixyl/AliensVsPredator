package com.arisux.airi.coremod;

import com.arisux.airi.AIRI;
import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class AIRIAccessTransformer extends AccessTransformer
{
    public AIRIAccessTransformer() throws Exception
    {
        super("airi_at.cfg");
        AIRI.setASMInitialized(true);
        AIRI.logger.info("ASM: %s", this.getClass().getName());
    }
}
