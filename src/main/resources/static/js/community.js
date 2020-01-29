/**
 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    // console.log(questionId);
    var content = $("#comment_content").val();
    // console.log(content);
    comment2target(questionId, 1, content);
}

//异步提交
function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
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
                        window.localStorage.setItem("closable", true);
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

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    //获取一下二级评论的展开状态，按状态开关展示不同
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comments.removeClass("in");
        //去除二级评论展开状态、标签的颜色class
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        //先看是否加载过，加载过子元素是除了评论框以外有列表条目的，所以length>1
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            //标记二级评论展开状态、标签的颜色class
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            //首次加载
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    });
                    mediaElement.append(mediaLeftElement).append(mediaBodyElement);


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });

                //展开二级评论
                comments.addClass("in");
                //标记二级评论展开状态、标签的颜色class
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

//选择标签，放入input框
function selectTag(e) {
    var value=e.getAttribute("data-tag");
    var previous = $("#tag").val();
    //不存在的时候添加，添加时如果有第一个值则加，再添加
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }

}

//展示标签选择栏
function showSelectTag() {
    $("#select-tag").show();
}