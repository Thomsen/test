import Foundation

struct Post {
    let title: String
    var isPublished: Bool = false

    init(title: String) {
        self.title = title
    }
}

class Blog {
    let name: String
    let url: URL
    weak var owner: Blogger?
    // var owner: Blogger?  // loop references not invoke deinit

    var publishedPosts: [Post] = []

    var onPublish: ((_ post: Post) -> Void)?

    init(name: String, url: URL) {
        self.name = name;
        self.url = url

        // Adding a closure instead to handle published posts
        onPublish = { [weak self] post in
            self?.publishedPosts.append(post)
            print("Published post count is now: \(self?.publishedPosts.count)")
        }
    }

    deinit {
        print("Blog \(name) is being deinitialized")
    }

    // func publish(post: Post) {
    //     /// Faking a network request with this delay:
    //     DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
    //         self.publishedPosts.append(post)
    //         print("Published post count is now: \(self.publishedPosts.count)")
    //     }
    // }

    func publish(post: Post) {
        /// Faking a network request with this delay:
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) { [weak self] in
            self?.publishedPosts.append(post)
            print("Published post count is now: \(self?.publishedPosts.count)")
        }
    }

    func publishClosure(post: Post) {
        /// Faking a network request with this delay:
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.onPublish?(post)
        }
    }
}

class Blogger {
    let name: String
    var blog: Blog?

    init(name: String) { self.name = name }

    deinit {
        print("Blogger \(name) is being deinitialized")
    }
}

var blog: Blog? = Blog(name: "SwiftLee", url: URL(string: "www.avanderlee.com")!)
var blogger: Blogger? = Blogger(name: "Antoine van der Lee")

// loop references
blog!.owner = blogger
blogger!.blog = blog

blog!.publish(post: Post(title: "Explaining weak and unowned self"))

blog = nil
blogger = nil