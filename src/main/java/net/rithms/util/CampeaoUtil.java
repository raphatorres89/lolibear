package net.rithms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.Item;

public class CampeaoUtil {

	public static List<Champion> listarCampeoes(ChampionList lista) {
		List<Champion> campeoes = new ArrayList<>();
		for(Champion c: lista.getData().values()) {
			campeoes.add(c);
		}
		return organizarLista(campeoes);
	}
	
	public static List<Champion> organizarLista(List<Champion> campeoes) {
		Collections.sort(campeoes, new Comparator<Champion>() {
			public int compare(Champion c1, Champion c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return campeoes;
	}
	
	public static Iterator<Entry<String, Champion>> getIterarImages(ChampionList item) {
		Map<String, Champion> map = item.getData();
		Set<Entry<String, Champion>> set = map.entrySet();
		return set.iterator();
	}
	
	public static Iterator<Entry<String, Boolean>> getIterarMaps(Item item) {
		Map<String, Boolean> map = item.getMaps();
		Set<Entry<String, Boolean>> set = map.entrySet();
		return set.iterator();
	}

	/*public static Iterator<Entry<String, String>> getIterarEffects(Item item) {
		Map<String, String> map = item.getEffect();
		Set<Entry<String, String>> set = map.entrySet();
		return set.iterator();
	}*/

	/*public static String getJSON(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}*/

	/*public static ChampionMasteryDto[] getListaMaestriaCampeao(String userId) {
		String json = null;
		try {

			json = getJSON("https://br1.api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"
					+ userId + "?api_key=" + chave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ChampionMasteryDto[] championMastery = gson.fromJson(json, ChampionMasteryDto[].class);

		return championMastery;

	}

	public static ChampionMasteryDto getMaestriaCampeao(String userId, int campeao) {
		String json = null;
		try {

			json = getJSON("https://br1.api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"
					+ userId + "/by-champion/" + campeao + "?api_key=" + chave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ChampionMasteryDto championMastery = gson.fromJson(json, ChampionMasteryDto.class);

		return championMastery;

	}
	
	public static ItemListDto getListaItens() {
		String json = null;
		try {

			json = getJSON("https://global.api.riotgames.com/api/lol/static-data/BR/v1.2/item?itemListData=all&api_key="
					+ chave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ItemListDto listaDeItens = gson.fromJson(json, ItemListDto.class);

		return listaDeItens;

	}

	public static ItemDto getItemPorId(String id) {
		String json = null;
		try {

			json = getJSON("https://global.api.riotgames.com/api/lol/static-data/BR/v1.2/item/" + id
					+ "?itemData=all&api_key=" + chave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ItemDto itemPorId = gson.fromJson(json, ItemDto.class);

		return itemPorId;

	}
	
	public static ChampionListDto getListarAsImagensDeCampeao() {
		String json = null;
		try {

			json = getJSON("https://global.api.riotgames.com/api/lol/static-data/BR/v1.2/champion?champData=image&api_key=" + chave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ChampionListDto imagens = gson.fromJson(json, ChampionListDto.class);

		return imagens;
	}*/

}
