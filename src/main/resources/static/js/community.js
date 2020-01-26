function post() {

    var questionId = $("#question_id").val();
    // console.log(questionId);

    var content = $("#comment_content").val();
    // console.log(content);

    if(!content){
        alert("不能回复空内容~~~")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                //$("#comment_section").hide();
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    //判断是不是登录异常,是则重新登录
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=2fa0027ee18392156348&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true);
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}