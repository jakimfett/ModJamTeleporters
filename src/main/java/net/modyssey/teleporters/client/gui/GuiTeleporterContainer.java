package net.modyssey.teleporters.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.modyssey.teleporters.tileentities.TileEntityTeleporterController;
import net.modyssey.teleporters.tileentities.container.ContainerTeleporterController;

public class GuiTeleporterContainer extends GuiContainer {
    private TileEntityTeleporterController controller;

    public GuiTeleporterContainer(TileEntityTeleporterController controller) {
        super(new ContainerTeleporterController(controller));

        this.controller = controller;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

    }
}
