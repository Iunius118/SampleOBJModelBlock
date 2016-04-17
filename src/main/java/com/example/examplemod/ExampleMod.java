package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
	modid = ExampleMod.MOD_ID,
	name =ExampleMod.MOD_NAME,
	version = ExampleMod.MOD_VERSION,
	dependencies = ExampleMod.MOD_DEPENDENCIES,
	acceptedMinecraftVersions = ExampleMod.MOD_ACCEPTED_MC_VERSIONS )
public class ExampleMod {

	public static final String MOD_ID = "examplemod";
	public static final String MOD_NAME = "ExampleMod";
	public static final String MOD_VERSION = "1.0";
	public static final String MOD_DEPENDENCIES = "required-after:Forge@[1.9-12.16.0.1853,)";
	public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.9]";

	/* ブロックの登録名 */
	public static final ResourceLocation BLOCK_REGISTER_NAME = new ResourceLocation(MOD_ID, "sampleblock");
	public static Block sampleBlock;


	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		sampleBlock = new SampleBlock();
		/* ブロックの登録 */
		GameRegistry.register(sampleBlock, BLOCK_REGISTER_NAME);
		/* アイテムブロックの登録 */
		GameRegistry.register(new ItemBlock(sampleBlock), BLOCK_REGISTER_NAME);

		if (event.getSide().isClient()) {
			/* OBJファイルの配置先ドメイン名を登録 */
			OBJLoader.INSTANCE.addDomain(MOD_ID);
			/* インベントリ・手持ち描画用のModelResourceLocation登録。BlockStateのinventoryを指定 */
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sampleBlock), 0, new ModelResourceLocation(BLOCK_REGISTER_NAME, "inventory"));
		}
	}

	public class SampleBlock extends Block {

		public SampleBlock() {
			super(Material.rock);
			setCreativeTab(CreativeTabs.tabBlock);
			setUnlocalizedName("sampleblock");
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.CUTOUT_MIPPED;
		}

	}

}