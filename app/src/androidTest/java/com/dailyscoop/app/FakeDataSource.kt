package com.dailyscoop.app

import com.dailyscoop.app.data.database.model.ArticleEntity
import com.dailyscoop.app.data.database.model.subset.HeadlineSubSet

/**
 * TODO: create a new module for core_testing to make this singleton reusable to other testing modules
 */
object FakeDataSource {
    val localHeadline1 =
        HeadlineSubSet(
            id = 1,
            title = "F1: Max Verstappen wins US Grand Prix",
            topic = "news",
            publishedDate = "2022-10-24 23:45:30",
            mediaUrl = "https://sports.inquirer.net/files/2022/10/2022-10-24T015205Z_1_LYNXMPEI9N01J_RTROPTP_4_MOTOR-F1-USA-scaled.jpg",
            externalId = "2665f479d34776dc7220786ce0658b58",
        )

    val localHeadline2 =
        HeadlineSubSet(
            id = 2,
            title = "NBA: Anthony Edwards, Timberwolves blow by Thunder",
            topic = "news",
            publishedDate = "2022-10-24 23:38:07",
            mediaUrl = "https://sports.inquirer.net/files/2022/10/063_1244093160.jpg",
            externalId = "57fe599411e31393e29111b6510c8460",
        )

    val localNewsArticles by lazy {
        listOf(
            com.dailyscoop.app.FakeDataSource.localNewsArticle1,
            com.dailyscoop.app.FakeDataSource.localNewsArticle2,
        )
    }

    val localNewsArticle1 =
        ArticleEntity(
            id = 1,
            title = "NBA: Anthony Edwards, Timberwolves blow by Thunder",
            author = "Reuters",
            excerpt =
                "Anthony Edwards scored 30 points and grabbed 11 rebounds to lead the Minnesota Timberwolves" +
                    " to a 116-106 win over the host Oklahoma City Thunder on Sunday night. " +
                    "The Timberwolves led by as many",
            summary =
                "Anthony Edwards #1 of the Minnesota Timberwolves dribbles the ball while Josh Giddey #3 " +
                    "of the Oklahoma City Thunder defends in the fourth quarter of the game at Target Center on " +
                    "October 19, 2022 in Minneapolis, Minnesota. The Timberwolves defeated the Thunder 115-108. " +
                    "David Berding/Getty Images/AFP\nAnthony Edwards scored 30 points and grabbed 11 rebounds " +
                    "to lead the Minnesota Timberwolves to a 116-106 win over the host Oklahoma City Thunder " +
                    "on Sunday night.\nThe Timberwolves led by as many 18 early, but the Thunder steadily cut " +
                    "the deficit to as little as one by late in the third quarter.",
            topic = "news",
            publishedDate = "2022-10-24 23:38:07",
            originalNewsLink = "https://sports.inquirer.net/?p=482565",
            sourceLink = "inquirer.net",
            mediaUrl = "https://sports.inquirer.net/files/2022/10/063_1244093160.jpg",
            externalId = "2665f479d34776dc7220786ce0658b58",
        )

    private val localNewsArticle2 =
        ArticleEntity(
            id = 2,
            title = "NBA: Anthony Edwards, Timberwolves blow by Thunder",
            author = "Reuters",
            excerpt =
                "Anthony Edwards scored 30 points and grabbed 11 rebounds to lead the Minnesota Timberwolves " +
                    "to a 116-106 win over the host Oklahoma City Thunder on Sunday night. " +
                    "The Timberwolves led by as many",
            summary =
                "Anthony Edwards #1 of the Minnesota Timberwolves dribbles the ball while Josh Giddey #3 " +
                    "of the Oklahoma City Thunder defends in the fourth quarter of the game at Target Center " +
                    "on October 19, 2022 in Minneapolis, Minnesota. The Timberwolves defeated the Thunder 115-108. " +
                    "David Berding/Getty Images/AFP\nAnthony Edwards scored 30 points and grabbed 11 rebounds " +
                    "to lead the Minnesota Timberwolves to a 116-106 win over the host Oklahoma City Thunder " +
                    "on Sunday night.\nThe Timberwolves led by as many 18 early, but the Thunder steadily cut " +
                    "the deficit to as little as one by late in the third quarter.",
            topic = "news",
            publishedDate = "2022-10-24 23:38:07",
            originalNewsLink = "https://sports.inquirer.net/?p=482565",
            sourceLink = "inquirer.net",
            mediaUrl = "https://sports.inquirer.net/files/2022/10/063_1244093160.jpg",
            externalId = "57fe599411e31393e29111b6510c8460",
        )
}
