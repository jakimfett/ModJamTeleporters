package net.modyssey.teleporters.tileentities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.modyssey.teleporters.markets.IMarket;
import net.modyssey.teleporters.markets.IMarketFactory;
import net.modyssey.teleporters.markets.stock.StockList;
import net.modyssey.teleporters.tileentities.TileEntityTeleporterController;

import java.util.List;

public class ContainerTeleporterController extends Container {
    private TileEntityTeleporterController controller;
    private IMarket[] markets;
    private IMarketFactory[] marketFactories;
    private int marketIndex;

    public ContainerTeleporterController(TileEntityTeleporterController controller, IMarketFactory[] marketFactories) {
        this.controller = controller;
        this.marketFactories = marketFactories;
        this.marketIndex = 0;
    }

    public void initMarkets() {
        this.markets = new IMarket[marketFactories.length];

        for (int i = 0; i < marketFactories.length; i++) {
            this.markets[i] = marketFactories[i].createMarket();

            if (!controller.getWorldObj().isRemote)
                this.markets[i].initializeCart(controller);
        }
    }

    public int getMarketIndex() { return marketIndex; }
    public int getMarketCount() { return markets.length; }
    public String getMarketTitle(int i) { return markets[i].getMarketTitle(); }
    public void setMarketIndex(int index) { marketIndex = index; }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return controller.canInteractWith(var1);
    }

    public void updateMarketData(List<StockList> marketData) {
        if (markets == null || markets.length == 0)
            return;

        for (int i = 0; i < markets.length; i++) {
            if (marketData.size() <= i)
                break;

            markets[i].updateStock(marketData.get(i));
        }
    }

    public IMarket getCurrentMarket() {
        return markets[marketIndex];
    }
}
