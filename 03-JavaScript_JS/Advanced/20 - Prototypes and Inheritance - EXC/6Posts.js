function solution() {
    class Post {
        constructor(title, content) {
            this.title = title;
            this.content = content;
        }

        //ovverinding the protoype toString() method of class Post
        toString() {
            return `Post: ${this.title}\nContent: ${this.content}`;
        }
    }

    class SocialMediaPost extends Post {
        constructor(title, content, likes, dislikes) {
            super(title, content);
            this.likes = likes;
            this.dislikes = dislikes;
            this.comments = [];
        }

        addComment(comment) {
            this.comments.push(comment);
        }

        //overriding the toString() of class Post
        toString() {
            let result = super.toString() + `\nRating: ${this.likes - this.dislikes}`;
            if (this.comments.length == 0) {
                return result;
            }

            result += "\nComments:"
            this.comments.forEach(comm => {
                result += `\n * ${comm}`;
            });

            return result;
        }
    }


    class BlogPost extends Post{
        constructor(title, content, views){
            super(title, content);
            this._views = views;
        }

        view() {
            this._views += 1;
            return this;
        } //instan.view().view().view();

        toString() {
            return super.toString() + `\nViews: ${this._views}`;
        }
    }


    return { Post,  SocialMediaPost, BlogPost}
}


const classes = solution();
// let post = new classes.Post("Post", "Content");
// console.log(post.toString());
// Post: Post
// Content: Content

// let scm = new classes.SocialMediaPost("TestTitle", "TestContent", 25, 30);
// scm.addComment("Good post");
// scm.addComment("Very good post");
// scm.addComment("Wow!");
// console.log(scm.toString());

let blg = new classes.BlogPost("TestTitle", "TestContent", 10);
blg.view().view();
blg.view();
console.log(blg.toString());


