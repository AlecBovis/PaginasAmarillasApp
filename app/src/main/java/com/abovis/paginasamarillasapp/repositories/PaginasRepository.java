package com.abovis.paginasamarillasapp.repositories;

import com.abovis.paginasamarillasapp.models.Paginas;

import java.util.ArrayList;
import java.util.List;

public class PaginasRepository {

    private static List<Paginas> paginas = new ArrayList<>();

    static {
        paginas.add( new Paginas(100, "Hoteles", "Passarela Hotel",
                "Jr. Pelotillehue 53-48 Int 84","012242625","informes@passarelahotel.com",
                "www.vivamancora.com/peru/","logo1","Déjese cautivar por el mejor lugar para tomar sus vacaciones…"));
        paginas.add( new Paginas(200, "Restaurantes", "La Casa del Tacu Tacu",
                "Av. 20 de noviembre No. 1060","017584248","informes@lacasadeltacutacu.com",
                "www.larosanautica.com/","logo2","Déjese cautivar por el mejor sabor criollo…"));
        paginas.add( new Paginas(300, "Hoteles", "Hotel Mulan",
                "Av. 5 de mayo No. 1253","013547824","informes@hotelmulan.com",
                "www.vivamancora.com/peru/","logo3","Déjese cautivar por el mejor lugar para tomar sus vacaciones…"));
        paginas.add( new Paginas(400, "Restaurantes", "Deli Rico",
                "Jr. Pelotillehue 53-48 Int 84","016478224","informes@delirico.com",
                "www.larosanautica.com/","logo4","Déjese cautivar por el mejor sabor criollo…"));
        paginas.add( new Paginas(500, "Hoteles", "Platinum hotel",
                "Av. 5 de mayo No. 1253","017485236","informes@platinumhotel.com",
                "www.vivamancora.com/peru/","logo5","Déjese cautivar por el mejor lugar para tomar sus vacaciones…"));
        paginas.add( new Paginas(600, "Restaurantes", "Los mil y un platillos",
                "Av. 20 de noviembre No. 1060","014785426","informes@losmilyunplatillos.com",
                "www.larosanautica.com/","logo1","Déjese cautivar por el mejor sabor criollo…"));
        paginas.add( new Paginas(700, "Hoteles", "White Palace hotel",
                "Av. 5 de mayo No. 1253","017894578","informes@WhitePalacehotel.com",
                "www.vivamancora.com/peru/","logo2","Déjese cautivar por el mejor lugar para tomar sus vacaciones…"));
        paginas.add( new Paginas(800, "Restaurantes", "Gastronomic Art",
                "Jr. Pelotillehue 53-48 Int 84","013547824","informes@gastronomicart.com",
                "www.larosanautica.com/","logo3","Déjese cautivar por el mejor sabor criollo…"));
        paginas.add( new Paginas(900, "Hoteles", "Eskimal hotel",
                "Av. 20 de noviembre No. 1060","017929203","informes@Eskimalhotel.com",
                "www.vivamancora.com/peru/","logo4","Déjese cautivar por el mejor lugar para tomar sus vacaciones…"));
        paginas.add( new Paginas(1000, "Restaurantes", "Addictive Restaurant",
                "Av. 5 de mayo No. 1253","019475842","informes@addictiverestaurant.com",
                "www.larosanautica.com/","logo5","Déjese cautivar por el mejor sabor criollo…"));
    }

    public static List<Paginas> getPaginas() {
        return paginas;
    }

    public static Paginas getPaginasById(Integer id){
        for (Paginas pagina: paginas) {
            if(pagina.getId().equals(id)) {
                return pagina;
            }
        }
        return null;
    }
}
