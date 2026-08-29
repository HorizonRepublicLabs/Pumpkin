package net.minecraft.client.renderer.feature.submit;

import net.minecraft.client.renderer.feature.FeatureRendererType;

public interface TranslucentSubmit extends SubmitNode {

    float distanceToCameraSq();

    FeatureRendererType<? extends TranslucentSubmit> featureType();
}
