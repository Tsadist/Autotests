package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UnionReportingTest {

    private Long id;
    private String name;

    @SerializedName("status_id")
    private Long statusId;
    @SerializedName("method_name")
    private String methodName;
    @SerializedName("project_id")
    private Long projectId;
    @SerializedName("session_id")
    private Long sessionId;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    private String env;
    private String browser;
    @SerializedName("author_id")
    private Long authorId;
}
