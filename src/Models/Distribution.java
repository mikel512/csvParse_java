package Models;

public class Distribution {
    String _mediaType;
    String _title;
    String _description;
    String _downloadUrl;
    String _accessUrl;

    public Distribution(String mediaType, String title, String description,
                        String downloadUrl, String accessUrl){
        _mediaType = mediaType;
        _title = title;
        _description = description;
        _downloadUrl = downloadUrl;
        _accessUrl = accessUrl;
    }
}
