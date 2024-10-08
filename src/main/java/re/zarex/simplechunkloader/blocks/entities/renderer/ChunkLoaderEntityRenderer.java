package re.zarex.simplechunkloader.blocks.entities.renderer;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.Vec3f;
import re.zarex.simplechunkloader.blocks.ChunkLoader;

public class ChunkLoaderEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {

    private static ItemStack stack = new ItemStack(ChunkLoader.WORLDITEM, 1);

    public ChunkLoaderEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 8.0;
        matrices.translate(0.5, 1.0 + offset, 0.5);
        //matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 2));

        int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
       // MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack,ModelTransformationMode.GROUND,lightAbove,overlay,matrices,vertexConsumers,entity.getWorld(),0);
        matrices.pop();
    }
}
