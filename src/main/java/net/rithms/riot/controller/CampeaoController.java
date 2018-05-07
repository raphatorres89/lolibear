package net.rithms.riot.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionSpell;
import net.rithms.util.CampeaoUtil;
import net.rithms.util.StringUtil;

@ManagedBean
@ViewScoped
public class CampeaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Champion> campeoes;
	private boolean render;
	private String selecionado;
	private Champion campeao;
	private RiotApi api = new RiotApi();
	private String[] botoesMagia = new String[]{"Q","W","E","R"};
	
	@PostConstruct
	public void init() {
		this.render = false;
		this.campeao = new Champion();
		try {
			/*this.campeao = api.getDataChampion(498, null, null, ChampData.ALL);*/
			campeoes = CampeaoUtil.listarCampeoes(api.getDataChampionList());
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
	}
	
	public char chave(String c) {
		return c.charAt(c.length() - 1);
	}
	
	public void atualizarCampeao() {
		this.render = true;
		try {
			campeao = pegarPeloNome();
			RequestContext.getCurrentInstance().execute("$('.carousel.carousel-slider').carousel({fullWidth: true});");
			RequestContext.getCurrentInstance().execute("$('.slider').slider();");
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
	}
	
	public Champion pegarPeloNome() throws RiotApiException {
		for (Champion c: campeoes) {
			if (c.getName().equals(this.selecionado)) {
				return api.getDataChampion(c.getId(), null, null, ChampData.ALL);
			}
		}
		return null;
	}
	
	public String preencherVariaveisDoFeitico(ChampionSpell spell) {
		String s = spell.getTooltip();
		s = StringUtil.arrumarVar(s, spell);
		s = StringUtil.arrumarTooltip(s);
		return s;
	}
	
	public String preencherCusto(ChampionSpell spell) {
		String s = spell.getCostType();
		return "Custo: " + spell.getCostBurn() + " " + StringUtil.arrumarVar(s, spell);
	}
	
	public List<Champion> getCampeoes() {
		return campeoes;
	}

	public void setCampeoes(List<Champion> campeoes) {
		this.campeoes = campeoes;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public String getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(String selecionado) {
		this.selecionado = selecionado;
	}

	public Champion getCampeao() {
		return campeao;
	}

	public void setCampeao(Champion campeao) {
		this.campeao = campeao;
	}

	public String[] getBotoesMagia() {
		return botoesMagia;
	}
	
}
