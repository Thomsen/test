import Foundation

 struct User: Codable
 {
     var firstName: String
     var lastName: String
     var country: String

     enum CodingKeys: String, CodingKey {
         case firstName = "first_name"
         case lastName = "last_name"
         case country
     }
 }

//  var user = User()
//  user.firstName = "Thomsen"
//  user.lastName = "Wang"
//  user.country = "China"

let user = User(firstName: "Thomsen", lastName: "Wang", country: "China")

 let encoder = JSONEncoder()
 encoder.outputFormatting = .prettyPrinted

 let jsonData = try! encoder.encode(user)
 let jsonString = String(data: jsonData, encoding: .utf8)!

print("Hello, world!")

print(jsonString)

let userJson = """
[
    {
        "first_name": "Arthur",
        "last_name": "Dent",
        "country": "CN"
    }, {
        "first_name": "Zaphod",
        "last_name": "Beeblebrox",
        "country": "US"
    }, {
        "first_name": "Marvin",
        "last_name": "The Paranoid Android",
        "country": "CA"
    }
]
"""

let userData = userJson.data(using: .utf8)!
let users = try! JSONDecoder().decode([User].self, from: userData)

for user in users {
    // value of type 'User' has no member 'first_name'
    //print(user.first_name)

    print(user.firstName)
}


let recipeJson = """
 {
    "name": "Spaghetti Bolognese",
    "author": "Reinder's Cooking Corner",
    "url": "https://cookingcorner.com/spaghetti-bolognese",
    "yield": 4,
    "ingredients": ["cheese", "love", "spaghetti", "beef", "tomatoes", "garlic"],
    "instructions": "Cook spaghetti, fry beef and garlic, add tomatoes, add love, eat!"
}
"""

struct Recipe: Codable {
    var name: String
    var author: String
    var url: String
    var yield: Int
    var ingredients: [String]
    var instructions: String
}

let recipeData = recipeJson.data(using: .utf8)!

let decoder = JSONDecoder()

// instance method 'decode(_:from:)' requires that 'Recipe' conform to 'Decodable'
let recipe = try! decoder.decode(Recipe.self, from: recipeData)

print(recipe.name)