(ns speclj.platform-clj-macros)

(defmacro when-not-bound [name & body]
    `(when-not (bound? (find-var '~name)) ~@body))

(defn -make-with [name body ctor bang?]
    (let [var-name (with-meta (symbol name) {:dynamic true})
                  unique-name (gensym "with")]
          `(do
                      (declare ~var-name)
                    (def ~unique-name (~ctor '~var-name '~unique-name (fn [] ~@body) ~bang?))
                    ~unique-name)))

(defmacro expected-larger-than-delta [expected actual delta]
    `(> (.abs (- (bigdec ~expected) (bigdec ~actual))) (.abs (bigdec ~delta))))

(def throwable 'Throwable)