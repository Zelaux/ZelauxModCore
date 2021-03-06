package mma.type.pixmap;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.PixmapPacker.*;
import arc.graphics.g2d.TextureAtlas.*;
import arc.struct.*;
import mindustry.graphics.*;
import mindustry.graphics.MultiPacker.*;

public class MultiPackerPixmapProcessor{
    private static ObjectMap<MultiPacker, PixmapProcessor> map=new ObjectMap<>();
    public static PixmapProcessor get(MultiPacker packer){
        return map.get(packer,()-> new PixmapProcessor(){
            @Override
            public void save(Pixmap pixmap, String path){
                String[] split = path.split("/");
                packer.add(PageType.main, split[split.length-1],pixmap);
            }

            @Override
            public Pixmap get(String name){
                return packer.get(name).crop();
            }

            @Override
            public boolean has(String name){
                return packer.has(name);
            }

            @Override
            public Pixmap get(TextureRegion region){
                return Core.atlas.getPixmap(region).crop();
            }

            @Override
            public void replace(String name, Pixmap image){
                save(image,name);
            }

            @Override
            public void replace(TextureRegion name, Pixmap image){
                save(image, ((AtlasRegion)name).name);
            }

            @Override
            public void delete(String name){
                for(PageType type : PageType.all){
                    for(Page page : packer.getPacker(type).getPages()){
                        page.getRects().remove(name);
                    }
                }
//                PixmapRegion region = packer.get(name);
            }
        });
    }
}
