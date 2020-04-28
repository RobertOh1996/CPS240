package application;

class YoutubeModel {

	private String title;
	private String url;

	public YoutubeModel(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return title;
	}

}