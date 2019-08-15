package jp.mkserver.cscommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class CSCommand extends JavaPlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        Player p = (Player)sender;
        if(args.length==1){
            if(!p.hasPermission("cscommand.command")){
                p.sendMessage("§cあなたには権限がありません");
                return true;
            }
            if(CrackShotAPI.isNameWeaponContain(args[0])){
                if(!p.hasPermission("cscommand.fire."+args[0])){
                    p.sendMessage("§cあなたには権限がありません");
                    return true;
                }
                CrackShotAPI.fire(p,args[0],true);
            }else{
                p.sendMessage("§cそのCrackShot Weaponは存在しません");
            }
            return true;
        }
        p.sendMessage("/cscmd [CSName] : CSNameの銃を強制的に打たせます");
        return true;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("cscmd").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
