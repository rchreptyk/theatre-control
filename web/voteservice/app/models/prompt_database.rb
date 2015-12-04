require 'thread'

class PromptDatabase
    include Singleton

    def initialize
        @answers = { }
        @mutex = Mutex.new
    end

    def current_question
        @question
    end

    def ask_question(question)
        @mutex.synchronize {
            @question = question
        }
    end

    def clear_question
        @mutex.synchronize {
            @question = nil
            @answers = { }
        }
    end

    def has_answered(user_id)
        return !@answers[user_id].nil?
    end

    def register_answer(user_id, name, vote)
        return if current_question.nil?

        @mutex.synchronize {
            @answers[user_id] = Response.new name, vote
        }
    end

    def answers
        @answers.values
    end
end